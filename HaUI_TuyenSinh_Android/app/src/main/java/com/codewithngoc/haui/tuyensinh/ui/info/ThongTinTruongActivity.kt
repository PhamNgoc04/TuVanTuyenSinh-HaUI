package com.codewithngoc.haui.tuyensinh.ui.info
import com.codewithngoc.haui.tuyensinh.*

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.codewithngoc.haui.tuyensinh.databinding.ActivityThongTinTruongBinding
import com.codewithngoc.haui.tuyensinh.viewmodel.HomeViewModel

class ThongTinTruongActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThongTinTruongBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThongTinTruongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = android.graphics.Color.parseColor("#B71C1C")

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.truongInfo.observe(this) { info ->
            if (info != null) {
                binding.tvTenTruong.text = info.tenTruong
                binding.tvDiaChi.text = "${info.duong}, ${info.quan}, ${info.thanhPho}"
                binding.tvMoTa.text = info.moTa
            } else {
                Toast.makeText(this, "Không có dữ liệu Thông tin Trường", Toast.LENGTH_SHORT).show()
                binding.tvMoTa.text = "Xin lỗi, hiện tại chưa thể kết nối tới cơ sở dữ liệu của Trường."
            }
        }
    }
}
