package com.codewithngoc.haui.tuyensinh.ui.auth
import com.codewithngoc.haui.tuyensinh.*
import com.codewithngoc.haui.tuyensinh.ui.main.MainActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.codewithngoc.haui.tuyensinh.databinding.ActivityLoginBinding
import com.codewithngoc.haui.tuyensinh.network.LoginRequest
import com.codewithngoc.haui.tuyensinh.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.haui_red)
        
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        val prefs = getSharedPreferences("haui_prefs", MODE_PRIVATE)
        if (prefs.getString("token", null) != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        setupObservers()

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(LoginRequest(username, password))
            }
        }

        binding.tvGoRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(this) { isLoading ->
            binding.btnLogin.isEnabled = !isLoading
            binding.btnLogin.text = if (isLoading) "ĐANG XỬ LÝ..." else "ĐĂNG NHẬP"
        }

        viewModel.error.observe(this) { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        viewModel.loginResult.observe(this) { result ->
            if (result?.status == "SUCCESS") {
                getSharedPreferences("haui_prefs", MODE_PRIVATE).edit()
                    .putString("token", result.token ?: "")
                    .putString("username", binding.etUsername.text.toString().trim())
                    .putString("accountId", result.accountId ?: "")
                    .apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}

