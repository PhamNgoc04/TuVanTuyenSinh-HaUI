package com.codewithngoc.haui.tuyensinh.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codewithngoc.haui.tuyensinh.R
import com.codewithngoc.haui.tuyensinh.databinding.ActivityAdminManageBinding
import com.codewithngoc.haui.tuyensinh.viewmodel.AdminViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText

/**
 * Base Abstract Activity cho tất cả Admin Management Screens.
 * Mỗi entity screen chỉ cần kế thừa và override các abstract methods.
 * T phải là Any (non-nullable) để tương thích với DiffUtil.
 */
abstract class BaseAdminManageActivity<T : Any> : AppCompatActivity() {

    protected lateinit var binding: ActivityAdminManageBinding
    protected lateinit var viewModel: AdminViewModel
    protected lateinit var adapter: AdminManageAdapter<T>

    /** Tiêu đề Toolbar (đổi tên để tránh đụng Activity.getTitle()) */
    abstract fun screenTitle(): String

    /** Gọi API để load danh sách */
    abstract fun loadList()

    /** Observe LiveData từ ViewModel, gọi submitList() khi có data */
    abstract fun observeList()

    /** Icon emoji hiển thị ở mỗi item */
    abstract fun getItemIcon(): String

    /** Chuyển item thành cặp (title, subtitle) để hiển thị ở list */
    abstract fun mapItemToDisplay(item: T): Pair<String, String>

    /** Lấy ID của item để gọi delete */
    abstract fun getItemId(item: T): String

    /** Hiển thị BottomSheet Thêm Mới */
    abstract fun showAddDialog()

    /** Hiển thị BottomSheet Sửa — điền sẵn data vào form */
    abstract fun showEditDialog(item: T)

    /** Gọi ViewModel delete */
    abstract fun performDelete(item: T)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminManageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = android.graphics.Color.parseColor("#B71C1C")
        binding.toolbar.title = screenTitle()
        binding.toolbar.setNavigationOnClickListener { finish() }

        viewModel = ViewModelProvider(this)[AdminViewModel::class.java]

        setupRecyclerView()
        setupObservers()

        binding.fabAdd.setOnClickListener { showAddDialog() }

        loadList()
        observeList()
    }

    @Suppress("UNCHECKED_CAST")
    private fun setupRecyclerView() {
        adapter = AdminManageAdapter<T>(
            iconEmoji = getItemIcon(),
            mapDisplay = { item: T -> mapItemToDisplay(item) },
            onEdit = { item: T -> showEditDialog(item) },
            onDelete = { item: T -> showDeleteConfirm(item) }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(this) { loading ->
            binding.progressBar.visibility = if (loading) android.view.View.VISIBLE else android.view.View.GONE
        }
        viewModel.actionStatus.observe(this) { res ->
            if (res != null) {
                if (res.status == "SUCCESS") {
                    Toast.makeText(this, "✅ Thành công!", Toast.LENGTH_SHORT).show()
                    loadList()
                } else {
                    Toast.makeText(this, "❌ Lỗi: ${res.message}", Toast.LENGTH_SHORT).show()
                }
                viewModel.resetActionStatus()
            }
        }
    }

    /** Hiển thị AlertDialog confirm xóa */
    protected fun showDeleteConfirm(item: T) {
        val (title, _) = mapItemToDisplay(item)
        AlertDialog.Builder(this)
            .setTitle("Xác nhận xóa")
            .setMessage("Bạn có chắc muốn xóa \"$title\" không?\nHành động này không thể hoàn tác.")
            .setPositiveButton("🗑️ Xóa") { _, _ -> performDelete(item) }
            .setNegativeButton("Hủy", null)
            .show()
    }

    /**
     * Helper: mở BottomSheet với danh sách field động.
     * @param fields List<Triple<fieldIndex, hint, prefilledText>>
     * @param onSave Callback nhận Map<fieldIndex, value>
     */
    protected fun openBottomSheet(
        title: String,
        fields: List<Triple<Int, String, String>>,
        onSave: (Map<Int, String>) -> Unit
    ) {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_edit_generic, null)
        dialog.setContentView(view)

        view.findViewById<android.widget.TextView>(R.id.tvDialogTitle).text = title

        val container = view.findViewById<android.widget.LinearLayout>(R.id.fieldContainer)
        val editTexts = mutableMapOf<Int, TextInputEditText>()

        fields.forEach { (index, hint, prefill) ->
            val density = resources.displayMetrics.density
            val marginPx = (12 * density).toInt()

            val til = com.google.android.material.textfield.TextInputLayout(this).apply {
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                    android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
                ).also { lp -> lp.bottomMargin = marginPx }
                this.hint = hint
            }
            val et = TextInputEditText(this).apply {
                setText(prefill)
            }
            editTexts[index] = et
            til.addView(et)
            container.addView(til)
        }

        view.findViewById<android.widget.Button>(R.id.btnDialogSave).setOnClickListener {
            val values = editTexts.mapValues { (_, et) -> et.text.toString().trim() }
            if (values.values.any { it.isEmpty() }) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            onSave(values)
            dialog.dismiss()
        }

        view.findViewById<android.widget.Button>(R.id.btnDialogCancel).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
