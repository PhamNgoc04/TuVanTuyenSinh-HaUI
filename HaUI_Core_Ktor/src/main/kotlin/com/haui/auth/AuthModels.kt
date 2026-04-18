package com.haui.auth

// Mô hình hứng dữ liệu gửi lên từ App Android
data class AuthRequest(
    val username: String,
    val password: String
)

// Mô hình trả kết quả Đăng nhập / Đăng ký về cho App
data class AuthResponse(
    val status: String,
    val token: String? = null,
    val role: String? = null,
    val accountId: String? = null,
    val message: String
)
