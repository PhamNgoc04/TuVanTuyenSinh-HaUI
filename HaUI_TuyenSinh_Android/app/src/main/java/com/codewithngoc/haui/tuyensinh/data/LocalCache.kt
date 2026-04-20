package com.codewithngoc.haui.tuyensinh.data

import android.content.Context
import com.codewithngoc.haui.tuyensinh.network.NganhHocItem
import com.codewithngoc.haui.tuyensinh.network.TinTucItem
import com.codewithngoc.haui.tuyensinh.network.TruongItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Local Cache: Giải pháp Offline-First đơn giản sử dụng SharedPreferences + Gson.
 * Giúp app hiển thị nội dung ngay lập tức mà không cần đợi API hoặc có thể xem khi mất mạng.
 */
object LocalCache {
    private const val PREF_CACHE = "haui_cache_v1"
    private const val KEY_TINTUC = "cache_tintuc"
    private const val KEY_NGANH = "cache_nganh"
    private const val KEY_TRUONG = "cache_truong"

    private val gson = Gson()

    // --- TIN TUC ---
    fun saveTinTuc(context: Context, list: List<TinTucItem>) {
        val json = gson.toJson(list)
        context.getSharedPreferences(PREF_CACHE, Context.MODE_PRIVATE).edit().putString(KEY_TINTUC, json).apply()
    }

    fun getTinTuc(context: Context): List<TinTucItem>? {
        val json = context.getSharedPreferences(PREF_CACHE, Context.MODE_PRIVATE).getString(KEY_TINTUC, null)
        if (json.isNullOrEmpty()) return null
        val type = object : TypeToken<List<TinTucItem>>() {}.type
        return try { gson.fromJson(json, type) } catch (e: Exception) { null }
    }

    // --- NGANH HOC ---
    fun saveNganhHoc(context: Context, list: List<NganhHocItem>) {
        val json = gson.toJson(list)
        context.getSharedPreferences(PREF_CACHE, Context.MODE_PRIVATE).edit().putString(KEY_NGANH, json).apply()
    }

    fun getNganhHoc(context: Context): List<NganhHocItem>? {
        val json = context.getSharedPreferences(PREF_CACHE, Context.MODE_PRIVATE).getString(KEY_NGANH, null)
        if (json.isNullOrEmpty()) return null
        val type = object : TypeToken<List<NganhHocItem>>() {}.type
        return try { gson.fromJson(json, type) } catch (e: Exception) { null }
    }

    // --- THONG TIN TRUONG ---
    fun saveThongTinTruong(context: Context, truong: TruongItem) {
        val json = gson.toJson(truong)
        context.getSharedPreferences(PREF_CACHE, Context.MODE_PRIVATE).edit().putString(KEY_TRUONG, json).apply()
    }

    fun getThongTinTruong(context: Context): TruongItem? {
        val json = context.getSharedPreferences(PREF_CACHE, Context.MODE_PRIVATE).getString(KEY_TRUONG, null)
        if (json.isNullOrEmpty()) return null
        return try { gson.fromJson(json, TruongItem::class.java) } catch (e: Exception) { null }
    }
}
