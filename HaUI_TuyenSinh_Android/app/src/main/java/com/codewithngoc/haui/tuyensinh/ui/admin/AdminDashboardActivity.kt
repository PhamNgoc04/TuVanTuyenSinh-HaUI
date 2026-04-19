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

        binding.cardHocPhi.setOnClickListener {
            showAddHocPhiDialog()
        }

        binding.cardQuyTrinh.setOnClickListener {
            showAddQuyTrinhDialog()
        }

        binding.cardHocBong.setOnClickListener {
            showAddHocBongDialog()
        }

        binding.cardChiTieu.setOnClickListener {
            showAddChiTieuDialog()
        }

        binding.cardNgheNghiep.setOnClickListener {
            showAddNgheNghiepDialog()
        }

        binding.cardPhuongThuc.setOnClickListener {
            showAddPhuongThucDialog()
        }

        binding.cardThongTinTruong.setOnClickListener {
            showUpdateTruongDialog()
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
    private fun showAddHocPhiDialog() {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_hoc_phi, null)
        dialog.setContentView(view)

        view.findViewById<android.widget.Button>(R.id.btnSave).setOnClickListener {
            val soTien = view.findViewById<TextInputEditText>(R.id.etSoTien).text.toString()
            val namHoc = view.findViewById<TextInputEditText>(R.id.etNamHoc).text.toString()
            if (soTien.isNotEmpty() && namHoc.isNotEmpty()) {
                viewModel.addHocPhi(soTien, namHoc)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    private fun showAddQuyTrinhDialog() {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_quy_trinh, null)
        dialog.setContentView(view)

        view.findViewById<android.widget.Button>(R.id.btnSave).setOnClickListener {
            val noiDung = view.findViewById<TextInputEditText>(R.id.etNoiDung).text.toString()
            val tenBuoc = view.findViewById<TextInputEditText>(R.id.etTenBuoc).text.toString()
            if (noiDung.isNotEmpty()) {
                val finalContent = if (tenBuoc.isNotEmpty()) "$tenBuoc:\n$noiDung" else noiDung
                viewModel.addQuyTrinh(finalContent)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Vui lòng nhập nội dung", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    private fun showAddHocBongDialog() {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_hoc_bong, null)
        dialog.setContentView(view)

        view.findViewById<android.widget.Button>(R.id.btnSave).setOnClickListener {
            val ten = view.findViewById<TextInputEditText>(R.id.etTenHocBong).text.toString()
            val giaTri = view.findViewById<TextInputEditText>(R.id.etGiaTriHocBong).text.toString()
            val dieuKien = view.findViewById<TextInputEditText>(R.id.etDieuKien).text.toString()
            if (ten.isNotEmpty() && giaTri.isNotEmpty()) {
                viewModel.addHocBong(ten, giaTri, dieuKien)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Vui lòng nhập tên và giá trị học bổng", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    private fun showAddChiTieuDialog() {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_chi_tieu, null)
        dialog.setContentView(view)

        view.findViewById<android.widget.Button>(R.id.btnSave).setOnClickListener {
            val maNganh = view.findViewById<TextInputEditText>(R.id.etMaNganh).text.toString()
            val soChiTieu = view.findViewById<TextInputEditText>(R.id.etSoChiTieu).text.toString()
            val nam = view.findViewById<TextInputEditText>(R.id.etNam).text.toString()
            if (maNganh.isNotEmpty() && soChiTieu.isNotEmpty() && nam.isNotEmpty()) {
                viewModel.addChiTieu(maNganh, soChiTieu, nam)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    private fun showAddNgheNghiepDialog() {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_nghe_nghiep, null)
        dialog.setContentView(view)

        view.findViewById<android.widget.Button>(R.id.btnSave).setOnClickListener {
            val maNganh = view.findViewById<TextInputEditText>(R.id.etMaNganh).text.toString()
            val tenNghe = view.findViewById<TextInputEditText>(R.id.etTenNgheNghiep).text.toString()
            val moTa = view.findViewById<TextInputEditText>(R.id.etMoTa).text.toString()
            if (maNganh.isNotEmpty() && tenNghe.isNotEmpty()) {
                viewModel.addNgheNghiep(maNganh, tenNghe, moTa)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Vui lòng nhập Mã Ngành và Tên Nghề", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    private fun showAddPhuongThucDialog() {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_phuong_thuc, null)
        dialog.setContentView(view)

        view.findViewById<android.widget.Button>(R.id.btnSave).setOnClickListener {
            val ten = view.findViewById<TextInputEditText>(R.id.etTenPhuongThuc).text.toString()
            val moTa = view.findViewById<TextInputEditText>(R.id.etMoTa).text.toString()
            if (ten.isNotEmpty()) {
                viewModel.addPhuongThuc(ten, moTa)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Vui lòng nhập tên phương thức", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    private fun showUpdateTruongDialog() {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_update_truong, null)
        dialog.setContentView(view)

        view.findViewById<android.widget.Button>(R.id.btnSave).setOnClickListener {
            val tenTruong = view.findViewById<TextInputEditText>(R.id.etTenTruong).text.toString()
            val diaChi = view.findViewById<TextInputEditText>(R.id.etDiaChi).text.toString()
            val gioiThieu = view.findViewById<TextInputEditText>(R.id.etGioiThieu).text.toString()
            if (tenTruong.isNotEmpty()) {
                // MA_TRUONG cố định (hoặc lấy từ config)
                viewModel.updateTruong("HAUI", tenTruong, diaChi, gioiThieu)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Vui lòng nhập tên trường", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }
}

