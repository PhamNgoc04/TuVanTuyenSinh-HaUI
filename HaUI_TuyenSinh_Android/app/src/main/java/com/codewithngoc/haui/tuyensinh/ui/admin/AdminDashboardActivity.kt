package com.codewithngoc.haui.tuyensinh.ui.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codewithngoc.haui.tuyensinh.databinding.ActivityAdminDashboardBinding

class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = android.graphics.Color.parseColor("#B71C1C")
        binding.toolbar.setNavigationOnClickListener { finish() }

        setupCardClicks()
    }

    private fun setupCardClicks() {
        // ── Nhóm 1: Nội dung chính ──────────────────────────────────────────
        binding.cardTinTuc.setOnClickListener {
            startActivity(Intent(this, AdminTinTucManageActivity::class.java))
        }
        binding.cardNganhHoc.setOnClickListener {
            startActivity(Intent(this, AdminNganhHocManageActivity::class.java))
        }

        // ── Nhóm 2: Tài chính & Quy trình ───────────────────────────────────
        binding.cardHocPhi.setOnClickListener {
            startActivity(Intent(this, AdminHocPhiManageActivity::class.java))
        }
        binding.cardQuyTrinh.setOnClickListener {
            startActivity(Intent(this, AdminQuyTrinhManageActivity::class.java))
        }
        binding.cardHocBong.setOnClickListener {
            startActivity(Intent(this, AdminHocBongManageActivity::class.java))
        }

        // ── Nhóm 3: Theo ngành ───────────────────────────────────────────────
        binding.cardChiTieu.setOnClickListener {
            startActivity(Intent(this, AdminChiTieuManageActivity::class.java))
        }
        binding.cardNgheNghiep.setOnClickListener {
            startActivity(Intent(this, AdminNgheNghiepManageActivity::class.java))
        }
        binding.cardXetTuyen.setOnClickListener {
            startActivity(Intent(this, AdminPhuongThucManageActivity::class.java))
        }

        // ── Nhóm 4: Hệ thống ─────────────────────────────────────────────────
        binding.cardThongTinTruong.setOnClickListener {
            startActivity(Intent(this, AdminTruongManageActivity::class.java))
        }
        binding.cardNguoiDung.setOnClickListener {
            Toast.makeText(this, "⚙️ Tính năng quản lý Tài Khoản đang phát triển", Toast.LENGTH_SHORT).show()
        }
    }
}
