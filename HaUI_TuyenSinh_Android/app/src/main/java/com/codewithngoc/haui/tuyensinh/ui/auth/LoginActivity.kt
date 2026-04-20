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

        // ✅ Bug #1 Fix: Dùng AppPrefs constants thay magic string
        val prefs = getSharedPreferences(AppPrefs.PREF_MAIN, MODE_PRIVATE)
        if (prefs.getString(AppPrefs.KEY_TOKEN, null) != null) {
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
            // ✅ Warn #2 Fix: reset error sau khi show để tránh hiển lại sau rotation
            if (!msg.isNullOrEmpty()) {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                viewModel.clearError()
            }
        }

        viewModel.loginResult.observe(this) { result ->
            if (result?.status == "SUCCESS") {
                // ✅ Bug #1 Fix: Dùng AppPrefs + lưu KEY_ROLE để phân quyền Admin
                getSharedPreferences(AppPrefs.PREF_MAIN, MODE_PRIVATE).edit()
                    .putString(AppPrefs.KEY_TOKEN, result.token ?: "")
                    .putString(AppPrefs.KEY_USERNAME, binding.etUsername.text.toString().trim())
                    .putString(AppPrefs.KEY_ACCOUNT_ID, result.accountId ?: "")
                    .putString(AppPrefs.KEY_ROLE, result.role ?: "")  // ✅ Lưu role để HoSoFragment hiện nút Admin
                    .apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}

