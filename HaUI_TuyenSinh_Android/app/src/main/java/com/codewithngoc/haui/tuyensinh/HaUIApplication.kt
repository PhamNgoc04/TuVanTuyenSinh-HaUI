package com.codewithngoc.haui.tuyensinh

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class HaUIApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val prefs = getSharedPreferences("THEME_PREFS", Context.MODE_PRIVATE)
        val isDark = prefs.getBoolean("isDark", false)
        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}
