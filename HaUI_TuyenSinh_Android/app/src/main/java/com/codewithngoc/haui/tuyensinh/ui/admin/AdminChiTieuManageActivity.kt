package com.codewithngoc.haui.tuyensinh.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codewithngoc.haui.tuyensinh.R
import com.codewithngoc.haui.tuyensinh.databinding.ActivityAdminManageBinding
import com.codewithngoc.haui.tuyensinh.network.ChiTieuItem
import com.codewithngoc.haui.tuyensinh.network.NganhHocItem
import com.codewithngoc.haui.tuyensinh.viewmodel.AdminViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText

/**
 * Quản lý Chỉ Tiêu — cần chọn Ngành trước khi xem/thêm chỉ tiêu
 */
class AdminChiTieuManageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminManageBinding
    private lateinit var viewModel: AdminViewModel
    private lateinit var adapter: AdminManageAdapter<ChiTieuItem>

    private var nganhList = listOf<NganhHocItem>()
    private var selectedNganh: NganhHocItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminManageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = android.graphics.Color.parseColor("#B71C1C")
        binding.toolbar.title = "Quản lý Chỉ Tiêu"
        binding.toolbar.setNavigationOnClickListener { finish() }

        viewModel = ViewModelProvider(this)[AdminViewModel::class.java]

        setupAdapter()
        setupObservers()

        binding.fabAdd.setOnClickListener {
            if (selectedNganh == null) {
                Toast.makeText(this, "Vui lòng chọn ngành trước", Toast.LENGTH_SHORT).show()
            } else {
                showAddDialog()
            }
        }

        // Load danh sách ngành để chọn
        viewModel.fetchNganhHoc()
        viewModel.nganhHocList.observe(this) { list ->
            nganhList = list
            if (list.isNotEmpty() && selectedNganh == null) {
                selectedNganh = list[0]
                binding.toolbar.subtitle = "Ngành: ${list[0].tenNganh}"
                viewModel.fetchChiTieu(list[0].maNganh ?: "")
            }
        }

        // Click toolbar subtitle để đổi ngành
        binding.toolbar.setOnClickListener { showSelectNganhDialog() }
    }

    private fun setupAdapter() {
        adapter = AdminManageAdapter(
            iconEmoji = "📊",
            mapDisplay = { Pair("Năm ${it.nam ?: "?"} — ${it.soLuong ?: "?"} chỉ tiêu", it.phuongThuc ?: "") },
            onEdit = { showEditDialog(it) },
            onDelete = { showDeleteConfirm(it) }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(this) { loading ->
            binding.progressBar.visibility = if (loading) android.view.View.VISIBLE else android.view.View.GONE
        }
        viewModel.chiTieuList.observe(this) { list ->
            adapter.submitList(list)
            binding.layoutEmpty.visibility =
                if (list.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }
        viewModel.actionStatus.observe(this) { res ->
            if (res != null) {
                Toast.makeText(this, if (res.status == "SUCCESS") "✅ Thành công!" else "❌ ${res.message}", Toast.LENGTH_SHORT).show()
                selectedNganh?.let { viewModel.fetchChiTieu(it.maNganh ?: "") }
                viewModel.resetActionStatus()
            }
        }
    }

    private fun showSelectNganhDialog() {
        val names = nganhList.map { it.tenNganh ?: it.maNganh ?: "?" }.toTypedArray()
        AlertDialog.Builder(this)
            .setTitle("Chọn Ngành Học")
            .setItems(names) { _, idx ->
                selectedNganh = nganhList[idx]
                binding.toolbar.subtitle = "Ngành: ${nganhList[idx].tenNganh}"
                viewModel.fetchChiTieu(nganhList[idx].maNganh ?: "")
            }
            .show()
    }

    private fun showAddDialog() {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_edit_generic, null)
        dialog.setContentView(view)
        view.findViewById<android.widget.TextView>(R.id.tvDialogTitle).text = "➕ Thêm Chỉ Tiêu"
        val container = view.findViewById<android.widget.LinearLayout>(R.id.fieldContainer)
        fun addField(hint: String, prefill: String = ""): TextInputEditText {
            val til = com.google.android.material.textfield.TextInputLayout(this).apply {
                layoutParams = android.widget.LinearLayout.LayoutParams(-1, -2).also { it.bottomMargin = 32 }
                this.hint = hint
            }
            return TextInputEditText(this).apply { setText(prefill) }.also { til.addView(it); container.addView(til) }
        }
        val etSoLuong = addField("Số chỉ tiêu")
        val etNam = addField("Năm (vd: 2025)")
        val etPhuongThuc = addField("Phương thức xét tuyển")
        view.findViewById<android.widget.Button>(R.id.btnDialogSave).setOnClickListener {
            val sl = etSoLuong.text.toString().trim()
            val nam = etNam.text.toString().trim()
            val pt = etPhuongThuc.text.toString().trim()
            if (sl.isEmpty() || nam.isEmpty()) { Toast.makeText(this, "Nhập đủ thông tin", Toast.LENGTH_SHORT).show(); return@setOnClickListener }
            viewModel.addChiTieu(selectedNganh?.maNganh ?: "", sl, nam, pt)
            dialog.dismiss()
        }
        view.findViewById<android.widget.Button>(R.id.btnDialogCancel).setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun showEditDialog(item: ChiTieuItem) {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_edit_generic, null)
        dialog.setContentView(view)
        view.findViewById<android.widget.TextView>(R.id.tvDialogTitle).text = "✏️ Sửa Chỉ Tiêu"
        val container = view.findViewById<android.widget.LinearLayout>(R.id.fieldContainer)
        fun addField(hint: String, prefill: String = ""): TextInputEditText {
            val til = com.google.android.material.textfield.TextInputLayout(this).apply {
                layoutParams = android.widget.LinearLayout.LayoutParams(-1, -2).also { it.bottomMargin = 32 }
                this.hint = hint
            }
            return TextInputEditText(this).apply { setText(prefill) }.also { til.addView(it); container.addView(til) }
        }
        val etSoLuong = addField("Số chỉ tiêu", item.soLuong ?: "")
        val etNam = addField("Năm", item.nam ?: "")
        val etPhuongThuc = addField("Phương thức xét tuyển", item.phuongThuc ?: "")
        view.findViewById<android.widget.Button>(R.id.btnDialogSave).setOnClickListener {
            // ✅ Bug #2 Fix: dùng item.id thay item.nam để update đúng bản ghi
            val chiTieuId = item.id
            if (chiTieuId.isNullOrEmpty()) {
                Toast.makeText(this, "Lỗi: Không tìm thấy ID chỉ tiêu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.updateChiTieu(chiTieuId, etSoLuong.text.toString().trim(), etNam.text.toString().trim(), etPhuongThuc.text.toString().trim())
            dialog.dismiss()
        }
        view.findViewById<android.widget.Button>(R.id.btnDialogCancel).setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun showDeleteConfirm(item: ChiTieuItem) {
        // ✅ Bug #2 Fix: dùng item.id thay item.nam để delete đúng bản ghi
        val chiTieuId = item.id
        if (chiTieuId.isNullOrEmpty()) {
            Toast.makeText(this, "Lỗi: Không tìm thấy ID chỉ tiêu", Toast.LENGTH_SHORT).show()
            return
        }
        AlertDialog.Builder(this)
            .setTitle("Xác nhận xóa")
            .setMessage("Xóa chỉ tiêu năm ${item.nam}?")
            .setPositiveButton("Xóa") { _, _ -> viewModel.deleteChiTieu(chiTieuId) }
            .setNegativeButton("Hủy", null).show()
    }
}
