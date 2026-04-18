package com.codewithngoc.haui.tuyensinh.ui.auth
import com.codewithngoc.haui.tuyensinh.*

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.codewithngoc.haui.tuyensinh.databinding.ActivityRegisterBinding
import com.codewithngoc.haui.tuyensinh.network.LoginRequest
import com.codewithngoc.haui.tuyensinh.viewmodel.AuthViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.haui_red)
        binding.tvGoLogin.setOnClickListener { finish() }

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        setupObservers()

        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val rePassword = binding.etRePassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password != rePassword) {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.register(LoginRequest(username, password))
        }
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(this) { isLoading ->
            binding.btnRegister.isEnabled = !isLoading
            binding.btnRegister.text = if (isLoading) "Đang xử lý..." else "TẠO TÀI KHOẢN"
        }

        viewModel.error.observe(this) { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        viewModel.registerResult.observe(this) { res ->
            if (res?.status == "SUCCESS") {
                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}

