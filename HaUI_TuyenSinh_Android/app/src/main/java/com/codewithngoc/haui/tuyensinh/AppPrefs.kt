package com.codewithngoc.haui.tuyensinh

/**
 * Tập trung tất cả SharedPreferences keys vào 1 nơi duy nhất.
 * Tránh lỗi typo và key không khớp giữa các file.
 */
object AppPrefs {
    // Pref file names
    const val PREF_MAIN      = "haui_prefs"
    const val PREF_THEME     = "THEME_PREFS"

    // Keys trong PREF_MAIN (lưu khi login)
    const val KEY_TOKEN      = "token"
    const val KEY_ACCOUNT_ID = "accountId"
    const val KEY_USERNAME   = "username"
    const val KEY_ROLE       = "role"

    // Keys trong PREF_THEME
    const val KEY_IS_DARK    = "isDark"
}
