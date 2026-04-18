package com.codewithngoc.haui.tuyensinh.ui.admin
import com.codewithngoc.haui.tuyensinh.*

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.codewithngoc.haui.tuyensinh.databinding.ActivityAdminDashboardBinding
import com.codewithngoc.haui.tuyensinh.viewmodel.AdminViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText

class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminDashboardBinding
    private lateinit var viewModel: AdminViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = android.graphics.Color.parseColor("#B71C1C")
        binding.toolbar.setNavigationOnClickListener { finish() }

        viewModel = ViewModelProvider(this)[AdminViewModel::class.java]

        setupObservers()

        binding.cardTinTuc.setOnClickListener {
            showAddTinTucDialog()
        }

        binding.cardNganhHoc.setOnClickListener {
            showAddNganhHocDialog()
        }

        binding.cardNguoiDung.setOnClickListener {
            Toast.makeText(this, "Chức năng quản lý Users đang bảo trì", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupObservers() {
        viewModel.actionStatus.observe(this) { res ->
            if (res != null) {
                if (res.status == "SUCCESS") {
                    Toast.makeText(this, "Lưu thành công!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Lỗi: ${res.message}", Toast.LENGTH_SHORT).show()
                }
                viewModel.resetActionStatus()
            }
        }
    }

    private fun showAddTinTucDialog() {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_tin_tuc, null)
        dialog.setContentView(view)

        view.findViewById<android.widget.Button>(R.id.btnSave).setOnClickListener {
            val title = view.findViewById<TextInputEditText>(R.id.etTieuDe).text.toString()
            val desc = view.findViewById<TextInputEditText>(R.id.etMoTa).text.toString()
            val content = view.findViewById<TextInputEditText>(R.id.etNoiDung).text.toString()
            if (title.isNotEmpty() && content.isNotEmpty()) {
                viewModel.addTinTuc(title, content, desc)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    private fun showAddNganhHocDialog() {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_nganh, null)
        dialog.setContentView(view)

        view.findViewById<android.widget.Button>(R.id.btnSave).setOnClickListener {
            val ma = view.findViewById<TextInputEditText>(R.id.etMaNganh).text.toString()
            val ten = view.findViewById<TextInputEditText>(R.id.etTenNganh).text.toString()
            val moTa = view.findViewById<TextInputEditText>(R.id.etMoTaNganh).text.toString()
            if (ma.isNotEmpty() && ten.isNotEmpty()) {
                viewModel.addNganhHoc(ma, ten, moTa)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }
}

