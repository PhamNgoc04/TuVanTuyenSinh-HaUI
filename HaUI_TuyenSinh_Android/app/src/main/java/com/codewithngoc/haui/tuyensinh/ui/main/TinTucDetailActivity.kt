package com.codewithngoc.haui.tuyensinh.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codewithngoc.haui.tuyensinh.databinding.ActivityTinTucDetailBinding

class TinTucDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTinTucDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTinTucDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set status bar color
        window.statusBarColor = android.graphics.Color.parseColor("#B71C1C")

        // Handle back button
        binding.toolbar.setNavigationOnClickListener { finish() }

        // Get data from Intent
        val tieuDe = intent.getStringExtra("TIEU_DE") ?: "Không có tiêu đề"
        val moTa = intent.getStringExtra("MO_TA") ?: ""
        val noiDung = intent.getStringExtra("NOI_DUNG") ?: "Đang cập nhật nội dung chi tiết..."

        // Set text
        binding.tvTieuDe.text = tieuDe
        
        // Show Full content if available, else show description
        if (noiDung.isNotBlank() && noiDung != "null") {
            binding.tvNoiDung.text = noiDung
        } else {
            binding.tvNoiDung.text = moTa
        }
    }
}
