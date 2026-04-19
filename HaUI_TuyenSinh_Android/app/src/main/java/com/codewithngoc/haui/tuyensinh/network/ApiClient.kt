package com.codewithngoc.haui.tuyensinh.network

import android.content.Context
import com.codewithngoc.haui.tuyensinh.AppPrefs
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // Emulator: 10.0.2.2 | Device thật: IP máy tính
    private const val CORE_BASE_URL = "http://10.0.2.2:8080"
    private const val AI_BASE_URL   = "http://10.0.2.2:8000"

    // Context được inject từ HaUIApplication.init()
    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    /** OkHttpClient tự động đính kèm Authorization header từ SharedPrefs */
    private val authenticatedClient: OkHttpClient by lazy {
        val authInterceptor = Interceptor { chain ->
            val prefs = appContext.getSharedPreferences(AppPrefs.PREF_MAIN, Context.MODE_PRIVATE)
            val token = prefs.getString(AppPrefs.KEY_TOKEN, "") ?: ""
            val request = chain.request().newBuilder().apply {
                if (token.isNotEmpty()) {
                    addHeader("Authorization", "Bearer $token")
                }
            }.build()
            chain.proceed(request)
        }

        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    /** Client cho AI Service (không cần auth) */
    private val basicClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(CORE_BASE_URL)
            .client(authenticatedClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val aiInstance: AiApiService by lazy {
        Retrofit.Builder()
            .baseUrl(AI_BASE_URL)
            .client(basicClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AiApiService::class.java)
    }
}
