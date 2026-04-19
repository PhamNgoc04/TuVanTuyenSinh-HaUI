package com.codewithngoc.haui.tuyensinh

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.codewithngoc.haui.tuyensinh.network.ApiClient

class HaUIApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Khởi tạo ApiClient với context để dùng SharedPreferences lấy token
        ApiClient.init(this)

        // Khôi phục theme đã lưu
        val prefs = getSharedPreferences(AppPrefs.PREF_THEME, Context.MODE_PRIVATE)
        if (prefs.getBoolean(AppPrefs.KEY_IS_DARK, false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}
