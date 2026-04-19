package com.codewithngoc.haui.tuyensinh.ui.admin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.codewithngoc.haui.tuyensinh.databinding.ActivityAdminTruongManageBinding
import com.codewithngoc.haui.tuyensinh.network.ApiClient
import com.codewithngoc.haui.tuyensinh.viewmodel.AdminViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class AdminTruongManageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminTruongManageBinding
    private lateinit var viewModel: AdminViewModel
    private var maTruong: String = "HaUI"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminTruongManageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = android.graphics.Color.parseColor("#B71C1C")
        binding.toolbar.setNavigationOnClickListener { finish() }
        viewModel = ViewModelProvider(this)[AdminViewModel::class.java]

        loadTruongInfo()
        setupObservers()

        binding.btnSave.setOnClickListener { saveChanges() }
    }

    private fun loadTruongInfo() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val res = ApiClient.instance.getThongTinTruong().awaitResponse()
                val truong = res.body()?.data?.firstOrNull()
                if (truong != null) {
                    maTruong = truong.maTruong ?: "HaUI"
                    binding.etTenTruong.setText(truong.tenTruong)
                    binding.etMoTa.setText(truong.moTa)
                    binding.etThanhPho.setText(truong.thanhPho)
                    binding.etQuan.setText(truong.quan)
                    binding.etDuong.setText(truong.duong)
                }
            } catch (e: Exception) {
                Toast.makeText(this@AdminTruongManageActivity, "Lỗi tải thông tin: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupObservers() {
        viewModel.actionStatus.observe(this) { res ->
            if (res != null) {
                val msg = if (res.status == "SUCCESS") "✅ Cập nhật thành công!" else "❌ Lỗi: ${res.message}"
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                viewModel.resetActionStatus()
            }
        }
    }

    private fun saveChanges() {
        val body = mapOf(
            "tenTruong" to binding.etTenTruong.text.toString().trim(),
            "moTa" to binding.etMoTa.text.toString().trim(),
            "thanhPho" to binding.etThanhPho.text.toString().trim(),
            "quan" to binding.etQuan.text.toString().trim(),
            "duong" to binding.etDuong.text.toString().trim()
        )
        if (body["tenTruong"]!!.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên trường", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.updateTruong(maTruong, body)
    }
}
