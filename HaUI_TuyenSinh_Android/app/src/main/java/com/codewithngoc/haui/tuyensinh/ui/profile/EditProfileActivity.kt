package com.codewithngoc.haui.tuyensinh.ui.profile
import com.codewithngoc.haui.tuyensinh.*

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.codewithngoc.haui.tuyensinh.databinding.ActivityEditProfileBinding
import com.codewithngoc.haui.tuyensinh.viewmodel.ProfileViewModel

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private var profileId: String = ""
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = android.graphics.Color.parseColor("#B71C1C")
        binding.btnCancel.setOnClickListener { finish() }

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        // ✅ Đọc đúng pref name và key mà LoginActivity đã lưu
        val prefs = getSharedPreferences("haui_prefs", Context.MODE_PRIVATE)
        val accountId = prefs.getString("accountId", "") ?: ""

        setupObservers()

        if (accountId.isNotEmpty()) {
            // Hiển thị loading, khóa form trong lúc chờ data
            setFormEnabled(false)
            binding.btnSave.text = "ĐANG TẢI..."
            viewModel.loadProfile(accountId)
        } else {
            Toast.makeText(this, "Không tìm thấy tài khoản, vui lòng đăng nhập lại", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnSave.setOnClickListener {
            val hoTen = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            if (hoTen.isNotEmpty() && email.isNotEmpty()) {
                setFormEnabled(false)
                binding.btnSave.text = "ĐANG LƯU..."
                viewModel.updateProfile(profileId, hoTen, email)
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setFormEnabled(enabled: Boolean) {
        binding.etName.isEnabled = enabled
        binding.etEmail.isEnabled = enabled
        binding.btnSave.isEnabled = enabled
    }

    private fun setupObservers() {
        // ✅ Đổ sẵn dữ liệu hiện tại vào form khi load xong
        viewModel.userProfile.observe(this) { user ->
            if (user != null) {
                profileId = user.id ?: ""
                binding.etName.setText(user.ten ?: "")
                binding.etEmail.setText(user.email ?: "")
                // Mở khóa form cho phép chỉnh sửa
                setFormEnabled(true)
                binding.btnSave.text = "LƯU THÔNG TIN"
            } else {
                Toast.makeText(this, "Không thể tải thông tin hồ sơ", Toast.LENGTH_SHORT).show()
                setFormEnabled(true)
                binding.btnSave.text = "LƯU THÔNG TIN"
            }
        }

        viewModel.updateStatus.observe(this) { success ->
            if (success != null) {
                setFormEnabled(true)
                binding.btnSave.text = "LƯU THÔNG TIN"
                if (success) {
                    Toast.makeText(this, "✅ Cập nhật thành công!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "❌ Cập nhật thất bại, thử lại", Toast.LENGTH_SHORT).show()
                }
                viewModel.resetStatus()
            }
        }
    }
}
