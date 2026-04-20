# 🔨 REBUILD FROM SCRATCH — HaUI Tuyển Sinh Android

> **Mục tiêu:** Copy từng bước, build được app hoàn chỉnh  
> **Thời gian ước tính:** 8-12 giờ  
> **Yêu cầu:** Android Studio, JDK 17, Backend Ktor đang chạy port 8080

---

## 📋 TỔNG QUAN 8 MILESTONE

| # | Milestone | Kết quả |
|---|---|---|
| M0 | Project Setup | Gradle sync thành công |
| M1 | Network Layer | Gọi API được, có models |
| M2 | Repository Layer | Data layer sẵn sàng |
| M3 | ViewModel Layer | Business logic hoàn chỉnh |
| M4 | Auth UI | Login/Register chạy được |
| M5 | Main UI | 3 Fragment + BottomNav |
| M6 | Feature UI | Chat AI + Profile + Detail |
| M7 | Admin CRUD | Full quản trị 9 màn hình |
| M8 | Offline Cache & UX | App chạy tốc độ cao kể cả khi offline |

---

## MILESTONE 0 — PROJECT SETUP

### Bước 0.1 — Tạo Project Android Studio

```
File → New → New Project → Empty Views Activity
├── Name: HaUI TuyenSinh
├── Package: com.codewithngoc.haui.tuyensinh
├── Language: Kotlin
├── Min SDK: API 24
└── Build system: Gradle (Kotlin DSL)
```

### Bước 0.2 — Tạo package structure

Chuột phải vào package gốc → New → Package. Tạo lần lượt:
```
com.codewithngoc.haui.tuyensinh.network
com.codewithngoc.haui.tuyensinh.repository
com.codewithngoc.haui.tuyensinh.viewmodel
com.codewithngoc.haui.tuyensinh.ui.auth
com.codewithngoc.haui.tuyensinh.ui.main
com.codewithngoc.haui.tuyensinh.ui.course
com.codewithngoc.haui.tuyensinh.ui.chat
com.codewithngoc.haui.tuyensinh.ui.profile
com.codewithngoc.haui.tuyensinh.ui.info
com.codewithngoc.haui.tuyensinh.ui.admin
```

### Bước 0.3 — `app/build.gradle.kts`

```kotlin
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.codewithngoc.haui.tuyensinh"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.codewithngoc.haui.tuyensinh"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // MVVM
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // Network
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Navigation
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
}
```

### Bước 0.4 — `AndroidManifest.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.RECORD_AUDIO" />

    <application
        android:name=".HaUIApplication"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="HaUI Tuyển Sinh"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.HaUITuyenSinh"
        android:usesCleartextTraffic="true">

        <!-- LAUNCHER -->
        <activity android:name=".ui.auth.LoginActivity" android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <!-- AUTH -->
        <activity android:name=".ui.auth.RegisterActivity" android:exported="false" />

        <!-- MAIN -->
        <activity android:name=".ui.main.MainActivity" android:exported="false" />
        <activity android:name=".ui.main.TinTucDetailActivity" android:exported="false" />

        <!-- COURSE -->
        <activity android:name=".ui.course.NganhHocDetailActivity" android:exported="false" />

        <!-- CHAT -->
        <activity android:name=".ui.chat.AiChatActivity" android:exported="false" />

        <!-- PROFILE -->
        <activity android:name=".ui.profile.EditProfileActivity" android:exported="false" />

        <!-- INFO -->
        <activity android:name=".ui.info.ThongTinThietYeuActivity" android:exported="false" />

        <!-- ADMIN -->
        <activity android:name=".ui.admin.AdminDashboardActivity" android:exported="false" />
        <activity android:name=".ui.admin.AdminNganhHocManageActivity" android:exported="false" />
        <activity android:name=".ui.admin.AdminTinTucManageActivity" android:exported="false" />
        <activity android:name=".ui.admin.AdminHocPhiManageActivity" android:exported="false" />
        <activity android:name=".ui.admin.AdminQuyTrinhManageActivity" android:exported="false" />
        <activity android:name=".ui.admin.AdminHocBongManageActivity" android:exported="false" />
        <activity android:name=".ui.admin.AdminChiTieuManageActivity" android:exported="false" />
        <activity android:name=".ui.admin.AdminNgheNghiepManageActivity" android:exported="false" />
        <activity android:name=".ui.admin.AdminPhuongThucManageActivity" android:exported="false" />
        <activity android:name=".ui.admin.AdminTruongManageActivity" android:exported="false" />
    </application>
</manifest>
```

### Bước 0.5 — `AppPrefs.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh

object AppPrefs {
    const val PREF_MAIN      = "haui_prefs"
    const val PREF_THEME     = "THEME_PREFS"
    const val KEY_TOKEN      = "token"
    const val KEY_ACCOUNT_ID = "accountId"
    const val KEY_USERNAME   = "username"
    const val KEY_ROLE       = "role"
    const val KEY_IS_DARK    = "isDark"
}
```

### Bước 0.6 — `HaUIApplication.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.codewithngoc.haui.tuyensinh.network.ApiClient

class HaUIApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ApiClient.init(this)
        val prefs = getSharedPreferences(AppPrefs.PREF_THEME, Context.MODE_PRIVATE)
        if (prefs.getBoolean(AppPrefs.KEY_IS_DARK, false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}
```

> ✅ **Checkpoint M0:** Gradle sync thành công, không lỗi đỏ.

---

## MILESTONE 1 — NETWORK LAYER

### Bước 1.1 — `network/Models.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.network

// ===== AUTH =====
data class LoginRequest(val username: String, val password: String)

data class AuthResponse(
    val status: String,
    val token: String?,
    val role: String?,
    val accountId: String?,
    val message: String?
)

data class GenericResponse(val status: String, val message: String?)

// ===== TRUONG =====
data class TruongItem(
    val maTruong: String?, val tenTruong: String?,
    val moTa: String?, val thanhPho: String?,
    val quan: String?, val duong: String?
)
data class TruongListResponse(val status: String, val data: List<TruongItem>)

// ===== TIN TUC =====
data class TinTucItem(
    val id: String?, val tieuDe: String?,
    val anh: String?, val moTa: String?, val noiDung: String?
)
data class TinTucListResponse(val status: String, val data: List<TinTucItem>)

// ===== NGANH HOC =====
data class NganhHocItem(
    val maNganh: String?, val tenNganh: String?,
    val moTa: String?, val coSo: String?
)
data class NganhHocListResponse(val status: String, val data: List<NganhHocItem>)

// ===== NGHE NGHIEP =====
data class NgheNghiepItem(
    val maNghe: String?, val tenNghe: String?,
    val mucLuong: String?, val tinhTrang: String?
)
data class NgheNghiepListResponse(val status: String, val data: List<NgheNghiepItem>)

// ===== CHI TIEU =====
data class ChiTieuItem(val nam: String?, val soLuong: String?, val phuongThuc: String?)
data class ChiTieuListResponse(val status: String, val data: List<ChiTieuItem>)

// ===== YEU CAU DAU VAO =====
data class YeuCauDauVaoItem(val khoi: String?, val diemTong: String?)
data class YeuCauDauVaoListResponse(val status: String, val data: List<YeuCauDauVaoItem>)

// ===== TIN CHI =====
data class TinChiItem(val id: String?, val giaTien: String?)
data class TinChiListResponse(val status: String, val data: List<TinChiItem>)

// ===== HOC PHI =====
data class HocPhiItem(
    val id: String?, val soTien: String?,
    val namHoc: String?, val chuongTrinhHocId: String?
)
data class HocPhiListResponse(val status: String, val data: List<HocPhiItem>)

// ===== HOC BONG =====
data class HocBongItem(
    val id: String?, val loaiHb: String?,
    val diemYc: String?, val hanhKiemYc: String?
)
data class HocBongListResponse(val status: String, val data: List<HocBongItem>)

// ===== QUY TRINH NHAP HOC =====
data class QuyTrinhItem(val id: String?, val noiDung: String?)
data class QuyTrinhListResponse(val status: String, val data: List<QuyTrinhItem>)

// ===== CHUONG TRINH HOC =====
data class ChuongTrinhHocItem(val id: String?, val tenChuongTrinh: String?, val moTa: String?)
data class ChuongTrinhHocListResponse(val status: String, val data: List<ChuongTrinhHocItem>)

// ===== NGUOI DUNG =====
data class NguoiDungResponse(
    val id: String?, val ten: String?,
    val avatar: String?, val email: String?, val vaiTro: String?
)

// ===== PHUONG THUC XET TUYEN =====
data class PhuongThucItem(val id: String?, val tenPhuongThuc: String?, val moTa: String?)
data class PhuongThucListResponse(val status: String, val data: List<PhuongThucItem>)

// ===== ADMIN =====
data class TaiKhoanAdminItem(val id: String?, val username: String?, val role: String?)
data class AdminTaiKhoanResponse(val status: String, val data: List<TaiKhoanAdminItem>)

// ===== AI SERVICE =====
data class AiChatRequest(val text: String)
data class AiChatResponse(val question: String?, val reply: String?)
```

### Bước 1.2 — `network/ApiService.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.network

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // ===== AUTH =====
    @POST("/api/v1/auth/login")
    fun login(@Body request: LoginRequest): Call<AuthResponse>

    @POST("/api/v1/auth/register")
    fun register(@Body request: LoginRequest): Call<AuthResponse>

    // ===== TRUONG =====
    @GET("/api/v1/truong")
    fun getThongTinTruong(): Call<TruongListResponse>

    @GET("/api/v1/truong/tin-tuc")
    fun getTinTuc(): Call<TinTucListResponse>

    @GET("/api/v1/truong/nganh-hoc")
    fun getNganhHocByTruong(): Call<NganhHocListResponse>

    // ===== NGANH HOC =====
    @GET("/api/v1/nganh-hoc")
    fun getAllNganhHoc(): Call<NganhHocListResponse>

    @GET("/api/v1/nganh-hoc/{maNganh}")
    fun getChiTietNganhHoc(@Path("maNganh") maNganh: String): Call<NganhHocItem>

    @GET("/api/v1/nganh-hoc/{maNganh}/nghe-nghiep")
    fun getNgheNghiep(@Path("maNganh") maNganh: String): Call<NgheNghiepListResponse>

    @GET("/api/v1/nganh-hoc/{maNganh}/chi-tieu")
    fun getChiTieu(@Path("maNganh") maNganh: String): Call<ChiTieuListResponse>

    @GET("/api/v1/nganh-hoc/{maNganh}/yeu-cau-dau-vao")
    fun getYeuCauDauVao(@Path("maNganh") maNganh: String): Call<YeuCauDauVaoListResponse>

    @GET("/api/v1/nganh-hoc/{maNganh}/tin-chi")
    fun getTinChi(@Path("maNganh") maNganh: String): Call<TinChiListResponse>

    // ===== HOC PHI / HOC BONG =====
    @GET("/api/v1/hoc-phi")
    fun getHocPhi(): Call<HocPhiListResponse>

    @GET("/api/v1/hoc-bong")
    fun getHocBong(): Call<HocBongListResponse>

    @GET("/api/v1/quy-trinh-nhap-hoc")
    fun getQuyTrinh(): Call<QuyTrinhListResponse>

    @GET("/api/v1/chuong-trinh-hoc")
    fun getChuongTrinhHoc(): Call<ChuongTrinhHocListResponse>

    // ===== NGUOI DUNG =====
    @GET("/api/v1/nguoi-dung/{taiKhoanId}")
    fun getNguoiDung(@Path("taiKhoanId") taiKhoanId: String): Call<NguoiDungResponse>

    @PUT("/api/v1/nguoi-dung/{id}")
    fun updateNguoiDung(@Path("id") id: String, @Body body: Map<String, String>): Call<Map<String, String>>

    // ===== PHUONG THUC =====
    @GET("/api/v1/phuong-thuc-xet-tuyen")
    fun getPhuongThucXetTuyen(): Call<PhuongThucListResponse>

    // ===== ADMIN CRUD =====
    @GET("/api/v1/admin/tai-khoan")
    fun getTaiKhoanAdmin(): Call<AdminTaiKhoanResponse>

    @POST("/api/v1/admin/nganh-hoc")
    fun addNganhHocAdmin(@Body body: Map<String, String>): Call<GenericResponse>
    @PUT("/api/v1/admin/nganh-hoc/{ma}")
    fun updateNganhHocAdmin(@Path("ma") ma: String, @Body body: Map<String, String>): Call<GenericResponse>
    @DELETE("/api/v1/admin/nganh-hoc/{ma}")
    fun deleteNganhHocAdmin(@Path("ma") ma: String): Call<GenericResponse>

    @POST("/api/v1/admin/tin-tuc")
    fun addTinTucAdmin(@Body body: Map<String, String>): Call<GenericResponse>
    @PUT("/api/v1/admin/tin-tuc/{id}")
    fun updateTinTucAdmin(@Path("id") id: String, @Body body: Map<String, String>): Call<GenericResponse>
    @DELETE("/api/v1/admin/tin-tuc/{id}")
    fun deleteTinTucAdmin(@Path("id") id: String): Call<GenericResponse>

    @PUT("/api/v1/admin/truong/{ma}")
    fun updateTruongAdmin(@Path("ma") ma: String, @Body body: Map<String, String>): Call<GenericResponse>

    @POST("/api/v1/admin/hoc-phi")
    fun addHocPhiAdmin(@Body body: Map<String, String>): Call<GenericResponse>
    @PUT("/api/v1/admin/hoc-phi/{id}")
    fun updateHocPhiAdmin(@Path("id") id: String, @Body body: Map<String, String>): Call<GenericResponse>
    @DELETE("/api/v1/admin/hoc-phi/{id}")
    fun deleteHocPhiAdmin(@Path("id") id: String): Call<GenericResponse>

    @POST("/api/v1/admin/quy-trinh")
    fun addQuyTrinhAdmin(@Body body: Map<String, String>): Call<GenericResponse>
    @PUT("/api/v1/admin/quy-trinh/{id}")
    fun updateQuyTrinhAdmin(@Path("id") id: String, @Body body: Map<String, String>): Call<GenericResponse>
    @DELETE("/api/v1/admin/quy-trinh/{id}")
    fun deleteQuyTrinhAdmin(@Path("id") id: String): Call<GenericResponse>

    @POST("/api/v1/admin/hoc-bong")
    fun addHocBongAdmin(@Body body: Map<String, String>): Call<GenericResponse>
    @PUT("/api/v1/admin/hoc-bong/{id}")
    fun updateHocBongAdmin(@Path("id") id: String, @Body body: Map<String, String>): Call<GenericResponse>
    @DELETE("/api/v1/admin/hoc-bong/{id}")
    fun deleteHocBongAdmin(@Path("id") id: String): Call<GenericResponse>

    @POST("/api/v1/admin/chi-tieu")
    fun addChiTieuAdmin(@Body body: Map<String, String>): Call<GenericResponse>
    @PUT("/api/v1/admin/chi-tieu/{id}")
    fun updateChiTieuAdmin(@Path("id") id: String, @Body body: Map<String, String>): Call<GenericResponse>
    @DELETE("/api/v1/admin/chi-tieu/{id}")
    fun deleteChiTieuAdmin(@Path("id") id: String): Call<GenericResponse>

    @POST("/api/v1/admin/nghe-nghiep")
    fun addNgheNghiepAdmin(@Body body: Map<String, String>): Call<GenericResponse>
    @PUT("/api/v1/admin/nghe-nghiep/{id}")
    fun updateNgheNghiepAdmin(@Path("id") id: String, @Body body: Map<String, String>): Call<GenericResponse>
    @DELETE("/api/v1/admin/nghe-nghiep/{id}")
    fun deleteNgheNghiepAdmin(@Path("id") id: String): Call<GenericResponse>

    @POST("/api/v1/admin/phuong-thuc-xet-tuyen")
    fun addPhuongThucAdmin(@Body body: Map<String, String>): Call<GenericResponse>
    @PUT("/api/v1/admin/phuong-thuc-xet-tuyen/{id}")
    fun updatePhuongThucAdmin(@Path("id") id: String, @Body body: Map<String, String>): Call<GenericResponse>
    @DELETE("/api/v1/admin/phuong-thuc-xet-tuyen/{id}")
    fun deletePhuongThucAdmin(@Path("id") id: String): Call<GenericResponse>
}

interface AiApiService {
    @POST("/api/v1/ai/ask")
    fun askAI(@Body request: AiChatRequest): Call<AiChatResponse>
}
```

### Bước 1.3 — `network/ApiClient.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.network

import android.content.Context
import com.codewithngoc.haui.tuyensinh.AppPrefs
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // Emulator: 10.0.2.2 | Device thật: IP LAN của máy tính (vd: 192.168.1.x)
    private const val CORE_BASE_URL = "http://10.0.2.2:8080"
    private const val AI_BASE_URL   = "http://10.0.2.2:8000"

    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    private val authenticatedClient: OkHttpClient by lazy {
        val authInterceptor = Interceptor { chain ->
            val token = appContext
                .getSharedPreferences(AppPrefs.PREF_MAIN, Context.MODE_PRIVATE)
                .getString(AppPrefs.KEY_TOKEN, "") ?: ""
            val request = chain.request().newBuilder().apply {
                if (token.isNotEmpty()) addHeader("Authorization", "Bearer $token")
            }.build()
            chain.proceed(request)
        }
        OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(CORE_BASE_URL)
            .client(authenticatedClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    val aiInstance: AiApiService by lazy {
        Retrofit.Builder()
            .baseUrl(AI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(AiApiService::class.java)
    }
}
```

> ✅ **Checkpoint M1:** Build thành công, không lỗi import.

---

## MILESTONE 2 — REPOSITORY LAYER

### Bước 2.1 — `repository/AuthRepository.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.ApiClient
import com.codewithngoc.haui.tuyensinh.network.LoginRequest

class AuthRepository {
    fun login(req: LoginRequest) = ApiClient.instance.login(req)
    fun register(req: LoginRequest) = ApiClient.instance.register(req)
}
```

### Bước 2.2 — `repository/TruongRepository.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.ApiClient

class TruongRepository {
    fun getThongTinTruong() = ApiClient.instance.getThongTinTruong()
    fun getTinTuc() = ApiClient.instance.getTinTuc()
}
```

### Bước 2.3 — `repository/NganhHocRepository.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.ApiClient

class NganhHocRepository {
    fun getAllNganhHoc() = ApiClient.instance.getAllNganhHoc()
}
```

### Bước 2.4 — `repository/NganhRepository.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.ApiClient

class NganhRepository {
    private val api = ApiClient.instance
    fun getChiTiet(maNganh: String) = api.getChiTietNganhHoc(maNganh)
    fun getNgheNghiep(maNganh: String) = api.getNgheNghiep(maNganh)
    fun getChiTieu(maNganh: String) = api.getChiTieu(maNganh)
    fun getYeuCau(maNganh: String) = api.getYeuCauDauVao(maNganh)
    fun getTinChi(maNganh: String) = api.getTinChi(maNganh)
}
```

### Bước 2.5 — `repository/UserRepository.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.ApiClient

class UserRepository {
    fun getNguoiDung(id: String) = ApiClient.instance.getNguoiDung(id)
    fun updateNguoiDung(id: String, body: Map<String, String>) =
        ApiClient.instance.updateNguoiDung(id, body)
}
```

### Bước 2.6 — `repository/InfoRepository.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.ApiClient

class InfoRepository {
    private val api = ApiClient.instance
    fun getHocPhi() = api.getHocPhi()
    fun getHocBong() = api.getHocBong()
    fun getQuyTrinh() = api.getQuyTrinh()
    fun getChuongTrinhHoc() = api.getChuongTrinhHoc()
}
```

### Bước 2.7 — `repository/ChatAiRepository.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.AiChatRequest
import com.codewithngoc.haui.tuyensinh.network.ApiClient

class ChatAiRepository {
    fun askAi(req: AiChatRequest) = ApiClient.aiInstance.askAI(req)
}
```

### Bước 2.8 — `repository/AdminRepository.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.ApiClient

class AdminRepository {
    private val api = ApiClient.instance

    fun getNganhHoc() = api.getAllNganhHoc()
    fun addNganhHoc(body: Map<String, String>) = api.addNganhHocAdmin(body)
    fun updateNganhHoc(ma: String, body: Map<String, String>) = api.updateNganhHocAdmin(ma, body)
    fun deleteNganhHoc(ma: String) = api.deleteNganhHocAdmin(ma)

    fun getTinTuc() = api.getTinTuc()
    fun addTinTuc(body: Map<String, String>) = api.addTinTucAdmin(body)
    fun updateTinTuc(id: String, body: Map<String, String>) = api.updateTinTucAdmin(id, body)
    fun deleteTinTuc(id: String) = api.deleteTinTucAdmin(id)

    fun getThongTinTruong() = api.getThongTinTruong()
    fun updateTruong(ma: String, body: Map<String, String>) = api.updateTruongAdmin(ma, body)

    fun getHocPhi() = api.getHocPhi()
    fun addHocPhi(body: Map<String, String>) = api.addHocPhiAdmin(body)
    fun updateHocPhi(id: String, body: Map<String, String>) = api.updateHocPhiAdmin(id, body)
    fun deleteHocPhi(id: String) = api.deleteHocPhiAdmin(id)

    fun getQuyTrinh() = api.getQuyTrinh()
    fun addQuyTrinh(body: Map<String, String>) = api.addQuyTrinhAdmin(body)
    fun updateQuyTrinh(id: String, body: Map<String, String>) = api.updateQuyTrinhAdmin(id, body)
    fun deleteQuyTrinh(id: String) = api.deleteQuyTrinhAdmin(id)

    fun getHocBong() = api.getHocBong()
    fun addHocBong(body: Map<String, String>) = api.addHocBongAdmin(body)
    fun updateHocBong(id: String, body: Map<String, String>) = api.updateHocBongAdmin(id, body)
    fun deleteHocBong(id: String) = api.deleteHocBongAdmin(id)

    fun getChiTieu(maNganh: String) = api.getChiTieu(maNganh)
    fun addChiTieu(body: Map<String, String>) = api.addChiTieuAdmin(body)
    fun updateChiTieu(id: String, body: Map<String, String>) = api.updateChiTieuAdmin(id, body)
    fun deleteChiTieu(id: String) = api.deleteChiTieuAdmin(id)

    fun getNgheNghiep(maNganh: String) = api.getNgheNghiep(maNganh)
    fun addNgheNghiep(body: Map<String, String>) = api.addNgheNghiepAdmin(body)
    fun updateNgheNghiep(id: String, body: Map<String, String>) = api.updateNgheNghiepAdmin(id, body)
    fun deleteNgheNghiep(id: String) = api.deleteNgheNghiepAdmin(id)

    fun getPhuongThuc() = api.getPhuongThucXetTuyen()
    fun addPhuongThuc(body: Map<String, String>) = api.addPhuongThucAdmin(body)
    fun updatePhuongThuc(id: String, body: Map<String, String>) = api.updatePhuongThucAdmin(id, body)
    fun deletePhuongThuc(id: String) = api.deletePhuongThucAdmin(id)
}
```

> ✅ **Checkpoint M2:** Build thành công. Tất cả repository compile.

---

## MILESTONE 3 — VIEWMODEL LAYER

### Bước 3.1 — `viewmodel/AuthViewModel.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.viewmodel

import androidx.lifecycle.*
import com.codewithngoc.haui.tuyensinh.network.*
import com.codewithngoc.haui.tuyensinh.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()

    private val _loginResult = MutableLiveData<AuthResponse?>()
    val loginResult: LiveData<AuthResponse?> = _loginResult

    private val _registerResult = MutableLiveData<AuthResponse?>()
    val registerResult: LiveData<AuthResponse?> = _registerResult

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun login(req: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = repository.login(req).awaitResponse()
                if (res.isSuccessful) _loginResult.postValue(res.body())
                else _error.postValue("Sai tài khoản hoặc mật khẩu!")
            } catch (e: Exception) {
                _error.postValue("Lỗi kết nối: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun register(req: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = repository.register(req).awaitResponse()
                if (res.isSuccessful) _registerResult.postValue(res.body())
                else _error.postValue("Đăng ký thất bại!")
            } catch (e: Exception) {
                _error.postValue("Lỗi kết nối: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
```

### Bước 3.2 — `viewmodel/HomeViewModel.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.viewmodel

import androidx.lifecycle.*
import com.codewithngoc.haui.tuyensinh.network.*
import com.codewithngoc.haui.tuyensinh.repository.TruongRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class HomeViewModel : ViewModel() {
    private val repo = TruongRepository()

    private val _truongInfo = MutableLiveData<TruongItem?>()
    val truongInfo: LiveData<TruongItem?> = _truongInfo

    private val _tinTucList = MutableLiveData<List<TinTucItem>>()
    val tinTucList: LiveData<List<TinTucItem>> = _tinTucList

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init { loadAll() }

    private fun loadAll() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val r1 = repo.getThongTinTruong().awaitResponse()
                _truongInfo.postValue(r1.body()?.data?.firstOrNull())
                val r2 = repo.getTinTuc().awaitResponse()
                _tinTucList.postValue(r2.body()?.data ?: emptyList())
            } catch (e: Exception) {
                _error.postValue("Lỗi tải dữ liệu")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
```

### Bước 3.3 — `viewmodel/NganhViewModel.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.viewmodel

import androidx.lifecycle.*
import com.codewithngoc.haui.tuyensinh.network.*
import com.codewithngoc.haui.tuyensinh.repository.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class NganhViewModel : ViewModel() {
    private val nganhHocRepo = NganhHocRepository()
    private val nganhRepo = NganhRepository()

    private val _nganhList = MutableLiveData<List<NganhHocItem>?>()
    val nganhList: LiveData<List<NganhHocItem>?> = _nganhList

    private val _nganhDetail = MutableLiveData<NganhHocItem?>()
    val nganhDetail: LiveData<NganhHocItem?> = _nganhDetail

    private val _ngheNghiepList = MutableLiveData<List<NgheNghiepItem>>()
    val ngheNghiepList: LiveData<List<NgheNghiepItem>> = _ngheNghiepList

    private val _chiTieuList = MutableLiveData<List<ChiTieuItem>>()
    val chiTieuList: LiveData<List<ChiTieuItem>> = _chiTieuList

    private val _yeuCauList = MutableLiveData<List<YeuCauDauVaoItem>>()
    val yeuCauList: LiveData<List<YeuCauDauVaoItem>> = _yeuCauList

    private val _tinChiList = MutableLiveData<List<TinChiItem>>()
    val tinChiList: LiveData<List<TinChiItem>> = _tinChiList

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadAllNganhHoc() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = nganhHocRepo.getAllNganhHoc().awaitResponse()
                _nganhList.postValue(if (res.isSuccessful) res.body()?.data else null)
            } catch (e: Exception) {
                _nganhList.postValue(null)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun loadNganhDetailData(maNganh: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val d = nganhRepo.getChiTiet(maNganh).awaitResponse()
                _nganhDetail.postValue(d.body())

                val nn = nganhRepo.getNgheNghiep(maNganh).awaitResponse()
                _ngheNghiepList.postValue(nn.body()?.data ?: emptyList())

                val ct = nganhRepo.getChiTieu(maNganh).awaitResponse()
                _chiTieuList.postValue(ct.body()?.data ?: emptyList())

                val yc = nganhRepo.getYeuCau(maNganh).awaitResponse()
                _yeuCauList.postValue(yc.body()?.data ?: emptyList())

                val tc = nganhRepo.getTinChi(maNganh).awaitResponse()
                _tinChiList.postValue(tc.body()?.data ?: emptyList())
            } catch (e: Exception) {
                // ignore partial error — tabs hiện "Đang cập nhật"
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
```

### Bước 3.4 — `viewmodel/ProfileViewModel.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.viewmodel

import androidx.lifecycle.*
import com.codewithngoc.haui.tuyensinh.network.NguoiDungResponse
import com.codewithngoc.haui.tuyensinh.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class ProfileViewModel : ViewModel() {
    private val repository = UserRepository()

    private val _userProfile = MutableLiveData<NguoiDungResponse?>()
    val userProfile: LiveData<NguoiDungResponse?> = _userProfile

    private val _updateStatus = MutableLiveData<Boolean?>()
    val updateStatus: LiveData<Boolean?> = _updateStatus

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadProfile(accountId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = repository.getNguoiDung(accountId).awaitResponse()
                if (res.isSuccessful && res.body() != null) {
                    _userProfile.postValue(res.body())
                } else {
                    _error.postValue("Không thể tải hồ sơ (HTTP ${res.code()})")
                    _userProfile.postValue(null)
                }
            } catch (e: Exception) {
                _error.postValue("Lỗi kết nối: ${e.localizedMessage}")
                _userProfile.postValue(null)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun updateProfile(id: String, name: String, email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = repository.updateNguoiDung(id,
                    mapOf("ten" to name, "email" to email)).awaitResponse()
                _updateStatus.postValue(
                    res.isSuccessful && res.body()?.get("status") == "SUCCESS"
                )
            } catch (e: Exception) {
                _updateStatus.postValue(false)
                _error.postValue("Lỗi kết nối: ${e.localizedMessage}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun updateAvatar(id: String, avatarUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updateNguoiDung(id, mapOf("avatar" to avatarUrl)).awaitResponse()
            } catch (e: Exception) {
                _error.postValue("Không thể cập nhật ảnh")
            }
        }
    }

    fun resetStatus() { _updateStatus.value = null }
    fun clearError() { _error.value = null }
}
```

### Bước 3.5 — `viewmodel/ChatViewModel.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.viewmodel

import androidx.lifecycle.*
import com.codewithngoc.haui.tuyensinh.network.*
import com.codewithngoc.haui.tuyensinh.repository.ChatAiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class ChatViewModel : ViewModel() {
    private val repository = ChatAiRepository()

    private val _aiResponse = MutableLiveData<AiChatResponse?>()
    val aiResponse: LiveData<AiChatResponse?> = _aiResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun askAi(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = repository.askAi(AiChatRequest(query)).awaitResponse()
                if (res.isSuccessful) _aiResponse.postValue(res.body())
                else _error.postValue("Lỗi phản hồi từ AI")
            } catch (e: Exception) {
                _error.postValue("Lỗi kết nối AI Service")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
```

### Bước 3.6 — `viewmodel/AdminViewModel.kt`

> ⚠️ File này dài ~250 dòng, xem file gốc trong repo: `viewmodel/AdminViewModel.kt`  
> **Pattern cốt lõi:**

```kotlin
// Helper giảm code lặp cho mọi action (add/update/delete)
private fun runAction(block: suspend () -> retrofit2.Response<*>) {
    viewModelScope.launch(Dispatchers.IO) {
        _isLoading.postValue(true)
        try {
            val res = block()
            _actionStatus.postValue(
                if (res.isSuccessful) ActionResult("SUCCESS", "OK")
                else ActionResult("FAIL", "HTTP ${res.code()}")
            )
        } catch (e: Exception) {
            _actionStatus.postValue(ActionResult("FAIL", e.message))
        } finally {
            _isLoading.postValue(false)
        }
    }
}

// Ví dụ dùng:
fun deleteNganhHoc(ma: String) = runAction { repository.deleteNganhHoc(ma).awaitResponse() }
fun fetchNganhHoc() { /* viewModelScope.launch → repo.getNganhHoc() → _nganhHocList */ }
```

> ✅ **Checkpoint M3:** Build thành công. Tất cả ViewModel compile.

---

## MILESTONE 4 — AUTH UI

### Bước 4.1 — Layout `activity_login.xml`

> Xem file gốc trong repo. Cấu trúc:
```
ConstraintLayout
├── ImageView logo / TextView "HaUI"
├── TextInputLayout → TextInputEditText id: etUsername
├── TextInputLayout → TextInputEditText id: etPassword
├── Button id: btnLogin
└── TextView id: tvGoRegister
```

### Bước 4.2 — `ui/auth/LoginActivity.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.ui.auth
import com.codewithngoc.haui.tuyensinh.*
import com.codewithngoc.haui.tuyensinh.ui.main.MainActivity

import android.content.Intent
import android.os.Bundle
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

        // Đã đăng nhập → skip
        val prefs = getSharedPreferences(AppPrefs.PREF_MAIN, MODE_PRIVATE)
        if (prefs.getString(AppPrefs.KEY_TOKEN, null) != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setupObservers()

        binding.btnLogin.setOnClickListener {
            val user = binding.etUsername.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()
            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(LoginRequest(user, pass))
            }
        }

        binding.tvGoRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(this) { loading ->
            binding.btnLogin.isEnabled = !loading
            binding.btnLogin.text = if (loading) "ĐANG XỬ LÝ..." else "ĐĂNG NHẬP"
        }

        viewModel.error.observe(this) { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        viewModel.loginResult.observe(this) { result ->
            if (result?.status == "SUCCESS") {
                getSharedPreferences(AppPrefs.PREF_MAIN, MODE_PRIVATE).edit()
                    .putString(AppPrefs.KEY_TOKEN, result.token ?: "")
                    .putString(AppPrefs.KEY_ACCOUNT_ID, result.accountId ?: "")
                    .putString(AppPrefs.KEY_ROLE, result.role ?: "")
                    .putString(AppPrefs.KEY_USERNAME, binding.etUsername.text.toString().trim())
                    .apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}
```

> ✅ **Checkpoint M4:** App khởi động, màn login hiện lên, đăng nhập thành công.

---

## MILESTONE 5 — MAIN UI

### Bước 5.1 — Layout `activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout ...>

    <FrameLayout
        android:id="@+id/fragmentContainer"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toTopOf="@id/bottomNavigation"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottomNavigation"
        android:id="@+id/bottom_navigation"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:menu="@menu/bottom_nav_menu" />

    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fabAiChatbot"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="16dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintBottom_toTopOf="@id/bottomNavigation"
        android:src="@android:drawable/ic_menu_send" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### Bước 5.2 — Menu `res/menu/bottom_nav_menu.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:id="@+id/nav_home"
        android:title="Trang chủ"
        android:icon="@android:drawable/ic_menu_today" />
    <item android:id="@+id/nav_news"
        android:title="Ngành học"
        android:icon="@android:drawable/ic_menu_agenda" />
    <item android:id="@+id/nav_profile"
        android:title="Hồ sơ"
        android:icon="@android:drawable/ic_menu_myplaces" />
</menu>
```

### Bước 5.3 — `ui/main/MainActivity.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.ui.main
import com.codewithngoc.haui.tuyensinh.*
import com.codewithngoc.haui.tuyensinh.ui.chat.AiChatActivity

import android.content.Intent
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codewithngoc.haui.tuyensinh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = getColor(R.color.haui_red)

        setupFab()
        setupBottomNavigation()

        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.nav_home
        }
    }

    private fun setupFab() {
        binding.fabAiChatbot.setOnClickListener {
            startActivity(Intent(this, AiChatActivity::class.java))
        }
        var dX = 0f; var dY = 0f; var isMoved = false
        binding.fabAiChatbot.setOnTouchListener { view, event ->
            when (event.actionMasked) {
                android.view.MotionEvent.ACTION_DOWN -> {
                    dX = view.x - event.rawX; dY = view.y - event.rawY; isMoved = false; true
                }
                android.view.MotionEvent.ACTION_MOVE -> {
                    val newX = event.rawX + dX; val newY = event.rawY + dY
                    if (kotlin.math.abs(view.x - newX) > 10 || kotlin.math.abs(view.y - newY) > 10) isMoved = true
                    view.x = newX.coerceIn(0f, (binding.root.width - view.width).toFloat())
                    view.y = newY.coerceIn(0f, (binding.root.height - view.height).toFloat())
                    true
                }
                android.view.MotionEvent.ACTION_UP -> {
                    if (!isMoved) view.performClick()
                    else {
                        val snapX = if (view.x + view.width / 2 > binding.root.width / 2)
                            binding.root.width.toFloat() - view.width - 24f else 24f
                        view.animate().x(snapX).setDuration(250)
                            .setInterpolator(DecelerateInterpolator()).start()
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home    -> { replaceFragment(HomeFragment()); true }
                R.id.nav_news    -> { replaceFragment(NganhHocFragment()); true }
                R.id.nav_profile -> { replaceFragment(HoSoFragment()); true }
                else             -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment).commit()
    }
}
```

### Bước 5.4 — Adapters tách file

**`ui/main/TinTucAdapter.kt`**
```kotlin
package com.codewithngoc.haui.tuyensinh.ui.main

import android.content.Intent
import android.view.*
import androidx.recyclerview.widget.*
import com.codewithngoc.haui.tuyensinh.databinding.ItemTinTucBinding
import com.codewithngoc.haui.tuyensinh.network.TinTucItem

class TinTucAdapter : ListAdapter<TinTucItem, TinTucAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<TinTucItem>() {
        override fun areItemsTheSame(a: TinTucItem, b: TinTucItem) = a.id == b.id
        override fun areContentsTheSame(a: TinTucItem, b: TinTucItem) = a == b
    }
) {
    inner class ViewHolder(val binding: ItemTinTucBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemTinTucBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.tvTieuDe.text = item.tieuDe
        holder.binding.tvMoTa.text = item.moTa
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, TinTucDetailActivity::class.java).apply {
                putExtra("TIEU_DE", item.tieuDe)
                putExtra("MO_TA", item.moTa)
                putExtra("NOI_DUNG", item.noiDung)
            }
            it.context.startActivity(intent)
        }
    }
}
```

**`ui/main/NganhAdapter.kt`**
```kotlin
package com.codewithngoc.haui.tuyensinh.ui.main

import android.content.Intent
import android.view.*
import androidx.recyclerview.widget.*
import com.codewithngoc.haui.tuyensinh.databinding.ItemNganhHocBinding
import com.codewithngoc.haui.tuyensinh.network.NganhHocItem
import com.codewithngoc.haui.tuyensinh.ui.course.NganhHocDetailActivity

class NganhAdapter : ListAdapter<NganhHocItem, NganhAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<NganhHocItem>() {
        override fun areItemsTheSame(a: NganhHocItem, b: NganhHocItem) = a.maNganh == b.maNganh
        override fun areContentsTheSame(a: NganhHocItem, b: NganhHocItem) = a == b
    }
) {
    inner class ViewHolder(val binding: ItemNganhHocBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemNganhHocBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.tvTenNganh.text = item.tenNganh
        holder.binding.tvMaNganh.text = "Mã ngành: ${item.maNganh}"
        holder.binding.tvMoTa.text = item.moTa
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, NganhHocDetailActivity::class.java).apply {
                putExtra("MA_NGANH", item.maNganh)
            }
            it.context.startActivity(intent)
        }
    }
}
```

> ✅ **Checkpoint M5:** App có BottomNav 3 tab, FAB có thể kéo thả.

---

## MILESTONE 6 — FEATURE UI

### Bước 6.1 — `ui/chat/AiChatActivity.kt`

> Xem file đầy đủ trong repo. Điểm quan trọng:
```kotlin
// ActivityResultLauncher thay startActivityForResult (deprecated)
private val speechLauncher = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult()
) { result ->
    if (result.resultCode == Activity.RESULT_OK) {
        val text = result.data
            ?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull()
        if (!text.isNullOrEmpty()) { addMessage(ChatMessage(text, true)); viewModel.askAi(text) }
    }
}
```

### Bước 6.2 — `ui/profile/EditProfileActivity.kt`

> Pattern prefill form:
```kotlin
val accountId = getSharedPreferences(AppPrefs.PREF_MAIN, MODE_PRIVATE)
    .getString(AppPrefs.KEY_ACCOUNT_ID, "") ?: ""

setFormEnabled(false)
binding.btnSave.text = "ĐANG TẢI..."
viewModel.loadProfile(accountId)

viewModel.userProfile.observe(this) { user ->
    if (user != null) {
        profileId = user.id ?: ""
        binding.etName.setText(user.ten ?: "")
        binding.etEmail.setText(user.email ?: "")
        setFormEnabled(true)
        binding.btnSave.text = "LƯU THÔNG TIN"
    }
}
```

### Bước 6.3 — `ui/course/NganhHocDetailActivity.kt`

> ViewPager2 + 5 tab. Điểm quan trọng:
```kotlin
// NganhDetailTabFragment - dùng activityViewModels (không cast Activity)
private val viewModel: NganhViewModel by activityViewModels()

// Html.fromHtml version-safe
cardView.tvDescription.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
    Html.fromHtml(desc, Html.FROM_HTML_MODE_LEGACY)
else @Suppress("DEPRECATION") Html.fromHtml(desc)
```

> ✅ **Checkpoint M6:** Chat AI hoạt động, detail ngành có 5 tab, edit profile prefill data.

---

## MILESTONE 7 — ADMIN CRUD

### Bước 7.1 — `ui/admin/BaseAdminManageActivity.kt` (Abstract base)

> Xem file đầy đủ trong repo. Đây là file phức tạp nhất.  
> Tóm tắt: abstract class generic `<T : Any>` với helper `openBottomSheet(...)`.

### Bước 7.2 — `ui/admin/AdminManageAdapter.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.ui.admin

import android.view.*
import androidx.recyclerview.widget.*
import com.codewithngoc.haui.tuyensinh.databinding.ItemAdminManageBinding

class AdminManageAdapter<T : Any>(
    private val iconEmoji: String,
    private val mapDisplay: (T) -> Pair<String, String>,
    private val onEdit: (T) -> Unit,
    private val onDelete: (T) -> Unit
) : ListAdapter<T, AdminManageAdapter<T>.ViewHolder>(
    object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(a: T, b: T) = a === b
        override fun areContentsTheSame(a: T, b: T) = a == b
    }
) {
    inner class ViewHolder(val binding: ItemAdminManageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemAdminManageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val (title, subtitle) = mapDisplay(item)
        holder.binding.tvIcon.text = iconEmoji
        holder.binding.tvTitle.text = title
        holder.binding.tvSubtitle.text = subtitle
        holder.binding.btnEdit.setOnClickListener { onEdit(item) }
        holder.binding.btnDelete.setOnClickListener { onDelete(item) }
    }
}
```

### Bước 7.3 — Ví dụ subclass đơn giản nhất

```kotlin
// AdminNganhHocManageActivity.kt (full code)
class AdminNganhHocManageActivity : BaseAdminManageActivity<NganhHocItem>() {

    override fun screenTitle() = "Quản lý Ngành Học"
    override fun getItemIcon() = "🎓"
    override fun loadList() = viewModel.fetchNganhHoc()

    override fun observeList() {
        viewModel.nganhHocList.observe(this) { list ->
            adapter.submitList(list)
            binding.layoutEmpty.visibility =
                if (list.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }
    }

    override fun mapItemToDisplay(item: NganhHocItem) =
        Pair(item.tenNganh ?: "—", "Mã: ${item.maNganh} • ${item.coSo ?: ""}")

    override fun getItemId(item: NganhHocItem) = item.maNganh ?: ""

    override fun showAddDialog() {
        openBottomSheet("➕ Thêm Ngành Học", listOf(
            Triple(0, "Tên ngành", ""),
            Triple(1, "Mã ngành", ""),
            Triple(2, "Cơ sở đào tạo", ""),
            Triple(3, "Mô tả", "")
        )) { values ->
            viewModel.addNganhHoc(values[0]!!, values[1]!!, values[2]!!, values[3]!!)
        }
    }

    override fun showEditDialog(item: NganhHocItem) {
        openBottomSheet("✏️ Sửa Ngành Học", listOf(
            Triple(0, "Tên ngành", item.tenNganh ?: ""),
            Triple(1, "Cơ sở đào tạo", item.coSo ?: ""),
            Triple(2, "Mô tả", item.moTa ?: "")
        )) { values ->
            viewModel.updateNganhHoc(item.maNganh ?: "", values[0]!!, values[1]!!, values[2]!!)
        }
    }

    override fun performDelete(item: NganhHocItem) =
        viewModel.deleteNganhHoc(item.maNganh ?: "")
}
```

> **Các màn hình Admin còn lại** (TinTuc, HocPhi, QuyTrinh, HocBong, PhuongThuc) đều copy pattern này — chỉ đổi entity type và field names.  
> **ChiTieu & NgheNghiep** kế thừa AppCompatActivity trực tiếp vì cần thêm selector ngành.

> ✅ **Checkpoint M7:** Admin dashboard hiện 9 cards, click vào mỗi card mở màn quản lý tương ứng.

---

## MILESTONE 8 — FINAL POLISH

### Bước 8.1 — Colors `res/values/colors.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="haui_red">#B71C1C</color>
    <color name="haui_red_dark">#7F0000</color>
    <color name="white">#FFFFFF</color>
    <color name="bg_page">#F5F5F5</color>
    <color name="bg_card">#FFFFFF</color>
    <color name="text_primary">#1A1A1A</color>
    <color name="text_secondary">#666666</color>
    <color name="text_caption">#999999</color>
</resources>
```

### Bước 8.2 — Theme `res/values/themes.xml`

```xml
<resources>
    <style name="Theme.HaUITuyenSinh" parent="Theme.MaterialComponents.DayNight.NoActionBar">
        <item name="colorPrimary">@color/haui_red</item>
        <item name="colorPrimaryDark">@color/haui_red_dark</item>
        <item name="colorAccent">@color/haui_red</item>
    </style>
</resources>
```

### Bước 8.3 — Shared Layout `activity_admin_manage.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout ...>
    <com.google.android.material.appbar.AppBarLayout ...>
        <androidx.appcompat.widget.Toolbar android:id="@+id/toolbar" ... />
    </com.google.android.material.appbar.AppBarLayout>
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView" ... />
    <LinearLayout android:id="@+id/layoutEmpty"
        android:visibility="gone" ...>
        <TextView android:text="Chưa có dữ liệu" ... />
    </LinearLayout>
    <ProgressBar android:id="@+id/progressBar" android:visibility="gone" ... />
    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fabAdd" ... />
</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

### Bước 8.4 — Shared Dialog `dialog_add_edit_generic.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout android:orientation="vertical" android:padding="24dp" ...>
    <TextView android:id="@+id/tvDialogTitle" android:textSize="18sp" ... />
    <LinearLayout android:id="@+id/fieldContainer" android:orientation="vertical" ... />
    <Button android:id="@+id/btnDialogSave" android:text="Lưu" ... />
    <Button android:id="@+id/btnDialogCancel" android:text="Hủy" ... />
</LinearLayout>
```

## MILESTONE 8 — OFFLINE-FIRST CACHE & UX BUGS

### Bước 8.1 — `data/LocalCache.kt`

```kotlin
package com.codewithngoc.haui.tuyensinh.data

import android.content.Context
import com.codewithngoc.haui.tuyensinh.network.TinTucItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object LocalCache {
    private const val PREF = "haui_cache_v1"
    private val gson = Gson()

    fun saveTinTuc(ctx: Context, list: List<TinTucItem>) {
        ctx.getSharedPreferences(PREF, 0).edit().putString("news", gson.toJson(list)).apply()
    }
    fun getTinTuc(ctx: Context): List<TinTucItem>? {
        val json = ctx.getSharedPreferences(PREF, 0).getString("news", null) ?: return null
        return try { gson.fromJson(json, object: TypeToken<List<TinTucItem>>(){}.type) } catch(e: Exception) { null }
    }
}
```

### Bước 8.2 — Tích hợp vào ViewModels

Trong `HomeViewModel.kt`:
```kotlin
fun loadData() {
    val ctx = HaUIApplication.appContext
    // 1. Tức tốc load cache lên UI
    LocalCache.getTinTuc(ctx)?.let { _tinTucList.postValue(it) }

    // 2. Chạy network ngầm
    viewModelScope.launch(Dispatchers.IO) {
        try {
            val res = api.getTinTuc().awaitResponse()
            val newData = res.body()?.data ?: emptyList()
            _tinTucList.postValue(newData)
            // 3. Cập nhật Cache mới
            LocalCache.saveTinTuc(ctx, newData)
        } catch (e: Exception) {
            _error.postValue("Đang xem chế độ offline (Mất kết nối)")
        }
    }
}
```

### Bước 8.3 — Sửa lỗi UX BottomNav (`MainActivity.kt`)

Dùng Show/Hide Framework thay vì tạo mới Fragment:
```kotlin
private var activeFragment: Fragment = homeFragment

private fun setupBottomNavigation() {
    supportFragmentManager.beginTransaction().apply {
        add(R.id.fragmentContainer, hoSoFragment).hide(hoSoFragment)
        add(R.id.fragmentContainer, nganhHocFragment).hide(nganhHocFragment)
        add(R.id.fragmentContainer, homeFragment)
    }.commit()

    binding.bottomNavigation.setOnItemSelectedListener { item ->
        val target = when(item.itemId) {
            R.id.nav_home -> homeFragment
            R.id.nav_news -> nganhHocFragment
            else -> hoSoFragment
        }
        supportFragmentManager.beginTransaction().hide(activeFragment).show(target).commit()
        activeFragment = target
        true
    }
}
```

> ✅ **Checkpoint M8:** App load cực nhanh, không bắt buộc có mạng vẫn mượt, đổi tab không bị giật lag.

---

## MILESTONE 9 — FINAL BUILD

### Bước 9.1 — Final Build & Git

```bash
# Build kiểm tra
./gradlew compileDebugKotlin

# Commit
git add .
git commit -m "feat: Complete HaUI TuyenSinh app"

# Push lên tất cả nhánh
git push origin main
git push origin main:develop main:develop-fix main:release
```

> ✅ **Checkpoint M8 — HOÀN THÀNH!** App có đầy đủ: Auth → Main → Detail → Chat → Profile → Admin CRUD.

---

## 📊 Tổng Kết File Structure

```
app/src/main/java/.../
├── AppPrefs.kt                          ← M0
├── HaUIApplication.kt                   ← M0
├── network/
│   ├── Models.kt                        ← M1
│   ├── ApiService.kt                    ← M1
│   └── ApiClient.kt                     ← M1
├── repository/
│   ├── AuthRepository.kt               ← M2
│   ├── TruongRepository.kt             ← M2
│   ├── NganhHocRepository.kt           ← M2
│   ├── NganhRepository.kt              ← M2
│   ├── UserRepository.kt               ← M2
│   ├── InfoRepository.kt               ← M2
│   ├── ChatAiRepository.kt             ← M2
│   └── AdminRepository.kt              ← M2
├── viewmodel/
│   ├── AuthViewModel.kt                ← M3
│   ├── HomeViewModel.kt                ← M3
│   ├── NganhViewModel.kt               ← M3
│   ├── ProfileViewModel.kt             ← M3
│   ├── ChatViewModel.kt                ← M3
│   ├── InfoViewModel.kt                ← M3
│   └── AdminViewModel.kt               ← M3
└── ui/
    ├── auth/LoginActivity.kt           ← M4
    ├── auth/RegisterActivity.kt        ← M4
    ├── main/MainActivity.kt            ← M5
    ├── main/HomeFragment.kt            ← M5
    ├── main/NganhHocFragment.kt        ← M5
    ├── main/HoSoFragment.kt            ← M5
    ├── main/TinTucAdapter.kt           ← M5
    ├── main/NganhAdapter.kt            ← M5
    ├── main/TinTucDetailActivity.kt    ← M5
    ├── main/TinTucListActivity.kt      ← M8
    ├── data/LocalCache.kt              ← M8
    ├── course/NganhHocDetailActivity.kt ← M6
    ├── chat/AiChatActivity.kt          ← M6
    ├── profile/EditProfileActivity.kt  ← M6
    ├── info/ThongTinThietYeuActivity.kt ← M6
    └── admin/
        ├── BaseAdminManageActivity.kt  ← M7
        ├── AdminManageAdapter.kt       ← M7
        ├── AdminDashboardActivity.kt   ← M7
        ├── AdminNganhHocManageActivity.kt ← M7
        ├── AdminTinTucManageActivity.kt   ← M7
        ├── AdminHocPhiManageActivity.kt   ← M7
        ├── AdminQuyTrinhManageActivity.kt ← M7
        ├── AdminHocBongManageActivity.kt  ← M7
        ├── AdminChiTieuManageActivity.kt  ← M7
        ├── AdminNgheNghiepManageActivity.kt ← M7
        ├── AdminPhuongThucManageActivity.kt ← M7
        └── AdminTruongManageActivity.kt   ← M7
```

---

*Tài liệu này dựa trên source code thực tế trong repo. Xem code gốc để có full implementation.*
