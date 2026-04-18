package com.haui.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date

object JwtConfig {
    private const val secret = "haui_tuyensinh_super_secret_key_2024" // Thông thường sẽ giấu trong file .env
    private const val issuer = "haui.com"
    private const val validityInMs = 36_000_000 * 10 // Thời hạn Token: ~100 tiếng

    val algorithm = Algorithm.HMAC512(secret)

    // Hàm tạo mã Token định danh (Sinh viên mang JWT này đi để app không bắt login lại)
    fun generateToken(username: String, role: String): String {
        return JWT.create()
            .withSubject("Authentication")
            .withIssuer(issuer)
            .withClaim("username", username)
            .withClaim("role", role)
            .withExpiresAt(getExpiration())
            .sign(algorithm)
    }

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)
}
