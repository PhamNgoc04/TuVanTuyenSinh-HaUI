package com.codewithngoc.haui.tuyensinh.ui.admin

import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.Bundle
import com.codewithngoc.haui.tuyensinh.R
import com.codewithngoc.haui.tuyensinh.databinding.ActivityAdminManageBinding
import com.codewithngoc.haui.tuyensinh.network.NganhHocItem
import com.codewithngoc.haui.tuyensinh.network.NgheNghiepItem
import com.codewithngoc.haui.tuyensinh.viewmodel.AdminViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText

class AdminNgheNghiepManageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminManageBinding
    private lateinit var viewModel: AdminViewModel
    private lateinit var adapter: AdminManageAdapter<NgheNghiepItem>

    private var nganhList = listOf<NganhHocItem>()
    private var selectedNganh: NganhHocItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminManageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = android.graphics.Color.parseColor("#B71C1C")
        binding.toolbar.title = "Quản lý Nghề Nghiệp"
        binding.toolbar.setNavigationOnClickListener { finish() }

        viewModel = ViewModelProvider(this)[AdminViewModel::class.java]

        adapter = AdminManageAdapter(
            iconEmoji = "💼",
            mapDisplay = { Pair(it.tenNghe ?: "—", "Lương: ${it.mucLuong ?: "?"} • ${it.tinhTrang ?: ""}") },
            onEdit = { showEditDialog(it) },
            onDelete = { confirmDelete(it) }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.isLoading.observe(this) { binding.progressBar.visibility = if (it) android.view.View.VISIBLE else android.view.View.GONE }
        viewModel.ngheNghiepList.observe(this) { list ->
            adapter.submitList(list)
            binding.layoutEmpty.visibility = if (list.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }
        viewModel.actionStatus.observe(this) { res ->
            if (res != null) {
                Toast.makeText(this, if (res.status == "SUCCESS") "✅ Thành công!" else "❌ ${res.message}", Toast.LENGTH_SHORT).show()
                selectedNganh?.let { viewModel.fetchNgheNghiep(it.maNganh ?: "") }
                viewModel.resetActionStatus()
            }
        }

        viewModel.fetchNganhHoc()
        viewModel.nganhHocList.observe(this) { list ->
            nganhList = list
            if (list.isNotEmpty() && selectedNganh == null) {
                selectedNganh = list[0]
                binding.toolbar.subtitle = "Ngành: ${list[0].tenNganh}"
                viewModel.fetchNgheNghiep(list[0].maNganh ?: "")
            }
        }

        binding.toolbar.setOnClickListener { showSelectNganhDialog() }
        binding.fabAdd.setOnClickListener {
            if (selectedNganh == null) Toast.makeText(this, "Chọn ngành trước", Toast.LENGTH_SHORT).show()
            else showAddDialog()
        }
    }

    private fun showSelectNganhDialog() {
        val names = nganhList.map { "${it.tenNganh} (${it.maNganh})" }.toTypedArray()
        AlertDialog.Builder(this).setTitle("Chọn Ngành").setItems(names) { _, i ->
            selectedNganh = nganhList[i]
            binding.toolbar.subtitle = "Ngành: ${nganhList[i].tenNganh}"
            viewModel.fetchNgheNghiep(nganhList[i].maNganh ?: "")
        }.show()
    }

    private fun showAddDialog() = showSheet("➕ Thêm Nghề Nghiệp", null)
    private fun showEditDialog(item: NgheNghiepItem) = showSheet("✏️ Sửa Nghề Nghiệp", item)

    private fun showSheet(title: String, item: NgheNghiepItem?) {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_edit_generic, null)
        dialog.setContentView(view)
        view.findViewById<android.widget.TextView>(R.id.tvDialogTitle).text = title
        val container = view.findViewById<android.widget.LinearLayout>(R.id.fieldContainer)
        fun f(hint: String, pre: String = ""): TextInputEditText {
            val til = com.google.android.material.textfield.TextInputLayout(this).apply {
                layoutParams = android.widget.LinearLayout.LayoutParams(-1, -2).also { it.bottomMargin = 32 }
                this.hint = hint
            }
            return TextInputEditText(this).apply { setText(pre) }.also { til.addView(it); container.addView(til) }
        }
        val etTen = f("Tên nghề nghiệp", item?.tenNghe ?: "")
        val etLuong = f("Mức lương trung bình", item?.mucLuong ?: "")
        val etTinhTrang = f("Tình trạng tuyển dụng", item?.tinhTrang ?: "")
        view.findViewById<android.widget.Button>(R.id.btnDialogSave).setOnClickListener {
            val ten = etTen.text.toString().trim()
            if (ten.isEmpty()) { Toast.makeText(this, "Nhập tên nghề", Toast.LENGTH_SHORT).show(); return@setOnClickListener }
            if (item == null) viewModel.addNgheNghiep(selectedNganh?.maNganh ?: "", ten, etLuong.text.toString().trim(), etTinhTrang.text.toString().trim())
            else viewModel.updateNgheNghiep(item.maNghe ?: "", ten, etLuong.text.toString().trim(), etTinhTrang.text.toString().trim())
            dialog.dismiss()
        }
        view.findViewById<android.widget.Button>(R.id.btnDialogCancel).setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun confirmDelete(item: NgheNghiepItem) {
        AlertDialog.Builder(this)
            .setTitle("Xác nhận xóa")
            .setMessage("Xóa nghề \"${item.tenNghe}\"?")
            .setPositiveButton("Xóa") { _, _ -> viewModel.deleteNgheNghiep(item.maNghe ?: "") }
            .setNegativeButton("Hủy", null).show()
    }
}
