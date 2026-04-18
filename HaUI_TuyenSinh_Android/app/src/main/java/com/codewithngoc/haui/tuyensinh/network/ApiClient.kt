package com.codewithngoc.haui.tuyensinh.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // Đổi IP thành IPv4 máy thật nếu chạy device thật (vd: 192.168.1.10)
    // Giữ 10.0.2.2 nếu chạy Emulator Android Studio
    private const val CORE_BASE_URL = "http://10.0.2.2:8080"
    private const val AI_BASE_URL   = "http://10.0.2.2:8000"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(CORE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val aiInstance: AiApiService by lazy {
        Retrofit.Builder()
            .baseUrl(AI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AiApiService::class.java)
    }
}
