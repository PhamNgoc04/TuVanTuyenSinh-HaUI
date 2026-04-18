package com.codewithngoc.haui.tuyensinh.ui.profile
import com.codewithngoc.haui.tuyensinh.*

import android.content.Context
import android.os.Bundle
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

        val sharedPref = getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
        val actId = sharedPref.getString("ACCOUNT_ID", "")

        setupObservers()

        if (!actId.isNullOrEmpty()) {
            viewModel.loadProfile(actId)
        }

        binding.btnSave.setOnClickListener {
            val hoTen = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            if (hoTen.isNotEmpty() && email.isNotEmpty()) {
                binding.btnSave.isEnabled = false
                binding.btnSave.text = "ĐANG LƯU..."
                viewModel.updateProfile(profileId, hoTen, email)
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupObservers() {
        viewModel.userProfile.observe(this) { p ->
            if (p != null) {
                profileId = p.id ?: ""
                binding.etName.setText(p.ten ?: "")
                binding.etEmail.setText(p.email ?: "")
            }
        }

        viewModel.updateStatus.observe(this) { success ->
            if (success != null) {
                binding.btnSave.isEnabled = true
                binding.btnSave.text = "LƯU THÔNG TIN"
                if (success) {
                    Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show()
                }
                viewModel.resetStatus()
            }
        }
    }
}
