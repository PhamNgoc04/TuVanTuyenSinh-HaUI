# 📘 DEVELOPMENT GUIDE — HaUI Tuyển Sinh Android

> **Mức độ:** Đầy đủ từ số 0 đến production  
> **Stack:** Kotlin · MVVM · Retrofit · Ktor Backend · FastAPI AI  
> **Tác giả:** codewithngoc

---

## 📋 MỤC LỤC

1. [Phân Tích Yêu Cầu & Thiết Kế Hệ Thống](#1-phân-tích-yêu-cầu--thiết-kế-hệ-thống)
2. [Thiết Kế CSDL & Backend API](#2-thiết-kế-csdl--backend-api)
3. [Khởi Tạo Android Project](#3-khởi-tạo-android-project)
4. [Cấu Hình Dependencies & Build](#4-cấu-hình-dependencies--build)
5. [Tầng Network — Models & ApiService](#5-tầng-network--models--apiservice)
6. [Tầng Repository](#6-tầng-repository)
7. [Tầng ViewModel](#7-tầng-viewmodel)
8. [Tầng UI — Auth (Login/Register)](#8-tầng-ui--auth-loginregister)
9. [Tầng UI — Main (Fragments)](#9-tầng-ui--main-fragments)
10. [Tầng UI — Chi Tiết Ngành Học](#10-tầng-ui--chi-tiết-ngành-học)
11. [Tầng UI — AI Chatbot](#11-tầng-ui--ai-chatbot)
12. [Tầng UI — Hồ Sơ & Cài Đặt](#12-tầng-ui--hồ-sơ--cài-đặt)
13. [Hệ Thống Admin — Full CRUD](#13-hệ-thống-admin--full-crud)
14. [Các Kỹ Thuật Nâng Cao & Tối Ưu](#14-các-kỹ-thuật-nâng-cao--tối-ưu)
15. [Git Workflow & Branching](#15-git-workflow--branching)
16. [Checklist Hoàn Thiện](#16-checklist-hoàn-thiện)

---

## 1. Phân Tích Yêu Cầu & Thiết Kế Hệ Thống

### 1.1 Use Case Diagram (chính)

```
┌─────────────────────────────────────────────────┐
│              HaUI Tuyển Sinh System              │
│                                                   │
│  [Thí sinh] ──► Xem thông tin trường             │
│             ──► Tra cứu ngành học                 │
│             ──► Xem học phí, học bổng             │
│             ──► Chat với AI tư vấn                │
│             ──► Quản lý hồ sơ cá nhân            │
│                                                   │
│  [Admin]    ──► Quản lý Ngành Học (CRUD)         │
│             ──► Quản lý Tin Tức (CRUD)           │
│             ──► Quản lý Học Phí (CRUD)           │
│             ──► Quản lý Học Bổng (CRUD)          │
│             ──► Quản lý Quy Trình (CRUD)         │
│             ──► Quản lý Chỉ Tiêu (CRUD)         │
│             ──► Quản lý Nghề Nghiệp (CRUD)      │
│             ──► Quản lý Xét Tuyển (CRUD)        │
│             ──► Cập nhật Thông Tin Trường        │
└─────────────────────────────────────────────────┘
```

### 1.2 Kiến Trúc Hệ Thống (3-tier)

```
┌──────────────┐     HTTP/REST      ┌─────────────────┐
│ Android App  │ ◄────────────────► │  Ktor Backend   │
│  (Kotlin)    │    Port 8080       │  (Kotlin/JVM)   │
└──────────────┘                    └────────┬────────┘
       │                                     │ SQL
       │ HTTP/REST                   ┌───────▼────────┐
       │    Port 8000                │  PostgreSQL DB  │
┌──────▼───────┐                    └────────────────┘
│  AI Service  │
│  (FastAPI)   │
│  LLM/RAG     │
└──────────────┘
```

### 1.3 Kiến Trúc Android — MVVM

```
┌─────────────────────────────────────────────────┐
│                   UI LAYER                       │
│  Activities · Fragments · Adapters · Layouts     │
│  Nhận events → gọi ViewModel → observe LiveData  │
├─────────────────────────────────────────────────┤
│               VIEWMODEL LAYER                    │
│  Chứa business logic · Quản lý UI state         │
│  Gọi Repository · Expose LiveData               │
├─────────────────────────────────────────────────┤
│               REPOSITORY LAYER                  │
│  Nguồn dữ liệu duy nhất (Single Source of Truth)│
│  Gọi ApiService · Trả về Call<T>               │
├─────────────────────────────────────────────────┤
│               NETWORK LAYER                     │
│  ApiClient (OkHttp + Retrofit)                  │
│  ApiService (interface endpoints)               │
│  Models (Data classes)                          │
└─────────────────────────────────────────────────┘
```

---

## 2. Thiết Kế CSDL & Backend API

### 2.1 Schema Database (PostgreSQL)

```sql
-- Bảng trường
CREATE TABLE truong (
    ma_truong VARCHAR PRIMARY KEY,
    ten_truong VARCHAR NOT NULL,
    mo_ta TEXT,
    thanh_pho VARCHAR,
    quan VARCHAR,
    duong VARCHAR
);

-- Bảng ngành học
CREATE TABLE nganh_hoc (
    ma_nganh VARCHAR PRIMARY KEY,
    ten_nganh VARCHAR NOT NULL,
    mo_ta TEXT,
    co_so VARCHAR,
    hinh_anh VARCHAR
);

-- Bảng tin tức
CREATE TABLE tin_tuc (
    id SERIAL PRIMARY KEY,
    tieu_de VARCHAR NOT NULL,
    mo_ta TEXT,
    noi_dung TEXT,
    ngay_dang TIMESTAMP DEFAULT now()
);

-- Bảng học phí
CREATE TABLE hoc_phi (
    id SERIAL PRIMARY KEY,
    ma_nganh VARCHAR REFERENCES nganh_hoc(ma_nganh),
    loai_hinh VARCHAR,      -- 'Đại trà', 'Chất lượng cao'
    gia_tien VARCHAR,
    don_vi VARCHAR DEFAULT 'VNĐ/tín chỉ'
);

-- Bảng học bổng
CREATE TABLE hoc_bong (
    id SERIAL PRIMARY KEY,
    ten_hoc_bong VARCHAR NOT NULL,
    dieu_kien TEXT,
    gia_tri VARCHAR,
    so_luong INTEGER
);

-- Bảng quy trình nhập học
CREATE TABLE quy_trinh_nhap_hoc (
    id SERIAL PRIMARY KEY,
    buoc INTEGER NOT NULL,
    ten_buoc VARCHAR NOT NULL,
    mo_ta TEXT
);

-- Bảng chỉ tiêu theo ngành
CREATE TABLE chi_tieu (
    id SERIAL PRIMARY KEY,
    ma_nganh VARCHAR REFERENCES nganh_hoc(ma_nganh),
    nam VARCHAR,
    so_luong VARCHAR,
    phuong_thuc VARCHAR
);

-- Bảng nghề nghiệp theo ngành
CREATE TABLE nghe_nghiep (
    ma_nghe VARCHAR PRIMARY KEY,
    ma_nganh VARCHAR REFERENCES nganh_hoc(ma_nganh),
    ten_nghe VARCHAR NOT NULL,
    muc_luong VARCHAR,
    tinh_trang VARCHAR
);

-- Bảng phương thức xét tuyển
CREATE TABLE phuong_thuc_xet_tuyen (
    id VARCHAR PRIMARY KEY,
    ten_phuong_thuc VARCHAR NOT NULL,
    mo_ta TEXT
);

-- Bảng tài khoản
CREATE TABLE tai_khoan (
    id VARCHAR PRIMARY KEY,
    ten_dang_nhap VARCHAR UNIQUE NOT NULL,
    mat_khau VARCHAR NOT NULL,   -- BCrypt hash
    vai_tro VARCHAR DEFAULT 'SINH_VIEN'
);

-- Bảng người dùng
CREATE TABLE nguoi_dung (
    id VARCHAR PRIMARY KEY REFERENCES tai_khoan(id),
    ten VARCHAR,
    email VARCHAR,
    avatar VARCHAR
);
```

### 2.2 REST API Endpoints (Ktor)

```
Auth:
POST   /api/v1/auth/login          → { token, accountId, status }
POST   /api/v1/auth/register       → { token, accountId, status }

Trường:
GET    /api/v1/truong               → TruongListResponse
PUT    /api/v1/truong/{maTruong}    → GenericResponse

Tin tức:
GET    /api/v1/truong/tin-tuc       → TinTucListResponse
POST   /api/v1/truong/tin-tuc       → TinTucItem
PUT    /api/v1/truong/tin-tuc/{id}  → TinTucItem
DELETE /api/v1/truong/tin-tuc/{id}  → GenericResponse

Ngành học:
GET    /api/v1/nganh-hoc            → NganhHocListResponse
POST   /api/v1/nganh-hoc            → NganhHocItem
PUT    /api/v1/nganh-hoc/{ma}       → NganhHocItem
DELETE /api/v1/nganh-hoc/{ma}       → GenericResponse
GET    /api/v1/nganh-hoc/{ma}       → NganhHocItem (detail)
GET    /api/v1/nganh-hoc/{ma}/chi-tieu      → ChiTieuListResponse
GET    /api/v1/nganh-hoc/{ma}/nghe-nghiep   → NgheNghiepListResponse
GET    /api/v1/nganh-hoc/{ma}/yeu-cau       → YeuCauListResponse
GET    /api/v1/nganh-hoc/{ma}/tin-chi       → TinChiListResponse

Học phí, Học bổng, Quy trình, Chỉ tiêu, Nghề nghiệp, Phương thức:
(tương tự pattern: GET list / POST / PUT /{id} / DELETE /{id})

Người dùng:
GET    /api/v1/nguoi-dung/{id}      → NguoiDungResponse
PUT    /api/v1/nguoi-dung/{id}      → GenericResponse

AI Service (FastAPI):
POST   /api/v1/ai/ask               → { reply: String }
```

---

## 3. Khởi Tạo Android Project

### 3.1 Tạo project mới trong Android Studio

```
File → New → New Project → Empty Views Activity
- Name: HaUI TuyenSinh
- Package: com.codewithngoc.haui.tuyensinh
- Language: Kotlin
- Min SDK: API 24 (Android 7.0)
- Build system: Gradle (Kotlin DSL)
```

### 3.2 Cấu trúc package (tạo thủ công)

```
Chuột phải vào package gốc → New → Package
Tạo các package sau:
  ├── network/
  ├── repository/
  ├── viewmodel/
  └── ui/
      ├── auth/
      ├── main/
      ├── course/
      ├── chat/
      ├── profile/
      ├── info/
      └── admin/
```

### 3.3 Tạo Application class

```kotlin
// HaUIApplication.kt
class HaUIApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ApiClient.init(this)
        // Restore theme preference
    }
}
```

**Đăng ký trong `AndroidManifest.xml`:**
```xml
<application
    android:name=".HaUIApplication"
    ... >
```

### 3.4 Cấu hình AndroidManifest.xml

```xml
<!-- Permissions -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />

<!-- Khai báo tất cả Activities -->
<activity android:name=".ui.auth.LoginActivity"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

---

## 4. Cấu Hình Dependencies & Build

### 4.1 `app/build.gradle.kts`

```kotlin
android {
    namespace = "com.codewithngoc.haui.tuyensinh"
    compileSdk = 36
    defaultConfig {
        applicationId = "com.codewithngoc.haui.tuyensinh"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        viewBinding = true    // BẮT BUỘC — thay thế findViewById
    }
    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    // AndroidX Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // MVVM Architecture Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // Navigation
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    // Network
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
}
```

---

## 5. Tầng Network — Models & ApiService

### 5.1 Tạo `AppPrefs.kt` — Constants tập trung

```kotlin
// Bước quan trọng: KHÔNG viết magic strings rải rác
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

### 5.2 Tạo `ApiClient.kt` — OkHttp + Retrofit + Auth Interceptor

```kotlin
object ApiClient {
    private const val CORE_BASE_URL = "http://10.0.2.2:8080"  // Emulator
    private const val AI_BASE_URL   = "http://10.0.2.2:8000"

    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    // Auth interceptor: tự động gắn Bearer token vào mọi request
    private val authenticatedClient: OkHttpClient by lazy {
        val authInterceptor = Interceptor { chain ->
            val token = appContext
                .getSharedPreferences(AppPrefs.PREF_MAIN, Context.MODE_PRIVATE)
                .getString(AppPrefs.KEY_TOKEN, "") ?: ""
            val request = chain.request().newBuilder()
                .apply { if (token.isNotEmpty()) addHeader("Authorization", "Bearer $token") }
                .build()
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

### 5.3 Tạo `Models.kt` — Data Classes

```kotlin
// Pattern chung cho mỗi entity: Item + ListResponse

// Auth
data class LoginRequest(val taiKhoan: String, val matKhau: String)
data class AuthResponse(val status: String, val token: String?, val accountId: String?)

// Người dùng
data class NguoiDungResponse(val id: String?, val ten: String?,
    val email: String?, val vaiTro: String?, val avatar: String?)

// Trường
data class TruongItem(val maTruong: String?, val tenTruong: String?,
    val moTa: String?, val thanhPho: String?, val quan: String?, val duong: String?)
data class TruongListResponse(val status: String?, val data: List<TruongItem>?)

// Ngành học
data class NganhHocItem(val maNganh: String?, val tenNganh: String?,
    val moTa: String?, val coSo: String?, val hinhAnh: String?)
data class NganhHocListResponse(val status: String?, val data: List<NganhHocItem>?)

// Tin tức
data class TinTucItem(val id: String?, val tieuDe: String?,
    val moTa: String?, val noiDung: String?)
data class TinTucListResponse(val status: String?, val data: List<TinTucItem>?)

// ... (tương tự cho HocPhi, HocBong, QuyTrinh, ChiTieu, NgheNghiep, ...)

// AI Chat
data class AiChatRequest(val query: String)
data class AiChatResponse(val reply: String?)
```

### 5.4 Tạo `ApiService.kt` — Retrofit Interface

```kotlin
interface ApiService {
    // Auth
    @POST("api/v1/auth/login")
    fun login(@Body req: LoginRequest): Call<AuthResponse>

    @POST("api/v1/auth/register")
    fun register(@Body req: LoginRequest): Call<AuthResponse>

    // Ngành học — Full CRUD
    @GET("api/v1/nganh-hoc")
    fun getNganhHoc(): Call<NganhHocListResponse>

    @POST("api/v1/nganh-hoc")
    fun addNganhHoc(@Body body: Map<String, String>): Call<NganhHocItem>

    @PUT("api/v1/nganh-hoc/{ma}")
    fun updateNganhHoc(@Path("ma") ma: String,
                       @Body body: Map<String, String>): Call<NganhHocItem>

    @DELETE("api/v1/nganh-hoc/{ma}")
    fun deleteNganhHoc(@Path("ma") ma: String): Call<Map<String, String>>

    // ... (tất cả endpoints khác tương tự)
}
```

---

## 6. Tầng Repository

### 6.1 Nguyên tắc

- **1 Repository = 1 domain** (Auth, Nganh, Truong, User, Admin, Chat)
- Repository KHÔNG xử lý exception — để ViewModel làm
- Trả về `Call<T>` để ViewModel dùng `awaitResponse()` trong Coroutine

### 6.2 Ví dụ `AuthRepository.kt`

```kotlin
class AuthRepository {
    fun login(req: LoginRequest) = ApiClient.instance.login(req)
    fun register(req: LoginRequest) = ApiClient.instance.register(req)
}
```

### 6.3 Ví dụ `AdminRepository.kt` — Nhiều entities

```kotlin
class AdminRepository {
    private val api = ApiClient.instance

    // Ngành học
    fun getNganhHoc() = api.getNganhHoc()
    fun addNganhHoc(body: Map<String, String>) = api.addNganhHoc(body)
    fun updateNganhHoc(ma: String, body: Map<String, String>) = api.updateNganhHoc(ma, body)
    fun deleteNganhHoc(ma: String) = api.deleteNganhHoc(ma)

    // Tin tức
    fun getTinTuc() = api.getTinTuc()
    fun addTinTuc(body: Map<String, String>) = api.addTinTuc(body)
    // ... (tất cả 9 entities)
}
```

---

## 7. Tầng ViewModel

### 7.1 Nguyên tắc

- **1 ViewModel = 1 màn hình** hoặc nhóm màn hình liên quan
- Dùng `viewModelScope.launch(Dispatchers.IO)` cho network calls
- Lộ trình: postValue trên IO thread → observe trên Main thread
- Luôn có: `_isLoading`, `_error`, và LiveData dữ liệu

### 7.2 Pattern chuẩn ViewModel

```kotlin
class SomeViewModel : ViewModel() {
    private val repository = SomeRepository()

    // State LiveDatas
    private val _data = MutableLiveData<List<Item>?>()
    val data: LiveData<List<Item>?> = _data

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = repository.getData().awaitResponse()
                if (res.isSuccessful) {
                    _data.postValue(res.body()?.data)
                } else {
                    _error.postValue("Lỗi HTTP ${res.code()}")
                }
            } catch (e: Exception) {
                _error.postValue("Lỗi kết nối: ${e.localizedMessage}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
```

### 7.3 AdminViewModel — Helper `runAction`

```kotlin
// Kỹ thuật quan trọng: Helper giảm code lặp
private fun runAction(block: suspend () -> Response<*>) {
    viewModelScope.launch(Dispatchers.IO) {
        _isLoading.postValue(true)
        try {
            val res = block()
            if (res.isSuccessful) {
                _actionStatus.postValue(ActionResult("SUCCESS", "OK"))
            } else {
                _actionStatus.postValue(ActionResult("FAIL", "HTTP ${res.code()}"))
            }
        } catch (e: Exception) {
            _actionStatus.postValue(ActionResult("FAIL", e.message))
        } finally {
            _isLoading.postValue(false)
        }
    }
}

// Dùng:
fun deleteNganhHoc(ma: String) = runAction { repository.deleteNganhHoc(ma).awaitResponse() }
fun addNganhHoc(ten: String, mo: String) = runAction {
    repository.addNganhHoc(mapOf("tenNganh" to ten, "moTa" to mo)).awaitResponse()
}
```

---

## 8. Tầng UI — Auth (Login/Register)

### 8.1 Layout `activity_login.xml`

```
- ConstraintLayout root
- Logo/Title HaUI
- TextInputLayout etUsername (+ icon)
- TextInputLayout etPassword (+ icon, inputType=textPassword)
- Button btnLogin
- TextView tvGoRegister (click để đến Register)
```

### 8.2 `LoginActivity.kt`

```kotlin
override fun onCreate(...) {
    // 1. Check token đã tồn tại → skip to MainActivity
    val prefs = getSharedPreferences(AppPrefs.PREF_MAIN, MODE_PRIVATE)
    if (prefs.getString(AppPrefs.KEY_TOKEN, null) != null) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        return
    }

    // 2. Observe ViewModel
    viewModel.loginResult.observe(this) { result ->
        if (result?.status == "SUCCESS") {
            // Lưu token + accountId vào SharedPreferences
            prefs.edit()
                .putString(AppPrefs.KEY_TOKEN, result.token)
                .putString(AppPrefs.KEY_ACCOUNT_ID, result.accountId)
                .apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    // 3. Click login
    binding.btnLogin.setOnClickListener {
        viewModel.login(LoginRequest(username, password))
    }
}
```

### 8.3 Luồng đăng nhập đầy đủ

```
User nhập tài khoản/mật khẩu
    ↓
LoginActivity.btnLogin.click()
    ↓
AuthViewModel.login(req)
    ↓ (IO thread)
AuthRepository.login(req) → Retrofit Call
    ↓ (response)
Lưu token vào SharedPreferences
    ↓
Navigate → MainActivity
```

---

## 9. Tầng UI — Main (Fragments)

### 9.1 `MainActivity.kt` — BottomNavigation + FAB

```kotlin
// Setup BottomNavigation
binding.bottomNavigation.setOnItemSelectedListener { item ->
    when (item.itemId) {
        R.id.nav_home    -> { replaceFragment(HomeFragment()); true }
        R.id.nav_news    -> { replaceFragment(NganhHocFragment()); true }
        R.id.nav_profile -> { replaceFragment(HoSoFragment()); true }
        else             -> false
    }
}

// FAB Draggable + Snap to edge
binding.fabAiChatbot.setOnTouchListener { view, event ->
    // ACTION_MOVE: di chuyển FAB
    // ACTION_UP: snap về cạnh gần nhất bằng animate()
}
```

### 9.2 `HomeFragment.kt` — Tin tức + Thông tin trường

```kotlin
// Dùng TinTucAdapter (ListAdapter + DiffUtil)
adapter = TinTucAdapter()
binding.rvTinTuc.adapter = adapter

viewModel.tinTucList.observe(viewLifecycleOwner) { list ->
    adapter.submitList(list)   // DiffUtil tự so sánh và update
}
```

### 9.3 `NganhHocFragment.kt` — List Ngành + Search không dấu

```kotlin
// Kỹ thuật tìm kiếm không dấu tiếng Việt
private fun removeAccents(str: String): String {
    val normalized = Normalizer.normalize(str, Normalizer.Form.NFD)
    return normalized
        .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
        .replace('đ', 'd').replace('Đ', 'D')
}

// Filter: so sánh query không dấu với tên ngành không dấu
val filtered = fullList.filter {
    removeAccents(it.tenNganh.lowercase()).contains(removeAccents(query))
}
adapter.submitList(filtered)
```

### 9.4 Generic Adapter Pattern (DiffUtil)

```kotlin
// Tất cả adapter đều là ListAdapter<T, VH> thay vì RecyclerView.Adapter
class TinTucAdapter : ListAdapter<TinTucItem, TinTucAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TinTucItem>() {
            override fun areItemsTheSame(old, new) = old.id == new.id
            override fun areContentsTheSame(old, new) = old == new
        }
    }

    // onCreateViewHolder, onBindViewHolder như bình thường
    // Nhưng: adapter.submitList(list) thay vì notifyDataSetChanged()
}
```

---

## 10. Tầng UI — Chi Tiết Ngành Học

### 10.1 ViewPager2 + TabLayout — 5 Tabs

```kotlin
// NganhHocDetailActivity.kt
val adapter = NganhDetailPagerAdapter(this, maNganh)
binding.viewPager.adapter = adapter

TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
    tab.text = when(pos) {
        0 -> "Chi tiết"
        1 -> "Nghề nghiệp"
        2 -> "Chỉ tiêu"
        3 -> "Yêu cầu"
        4 -> "Tín chỉ"
        else -> ""
    }
}.attach()
```

### 10.2 `NganhDetailTabFragment` — Dynamic card rendering

```kotlin
// Dùng activityViewModels() để share ViewModel với Activity
private val viewModel: NganhViewModel by activityViewModels()

// Render thẻ động từ data
fun addCard(title: String, desc: String, icon: Int) {
    val card = inflater.inflate(R.layout.item_detail_card, container, false)
    card.tvTitle.text = title
    card.tvDescription.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        Html.fromHtml(desc, Html.FROM_HTML_MODE_LEGACY)
    else Html.fromHtml(desc)
    container.addView(card)
}

// Observe từng tab
when(position) {
    0 -> viewModel.nganhDetail.observe(this) { detail -> renderDetail(detail) }
    1 -> viewModel.ngheNghiepList.observe(this) { list -> renderList(list) }
    // ...
}
```

---

## 11. Tầng UI — AI Chatbot

### 11.1 Layout Chat: 2 ViewHolder types

```kotlin
// ChatAdapter: TYPE_USER (1) vs TYPE_BOT (2)
override fun getItemViewType(pos: Int) =
    if (messages[pos].isUser) TYPE_USER else TYPE_BOT

override fun onCreateViewHolder(parent, viewType) =
    if (viewType == TYPE_USER)
        UserViewHolder(ItemChatUserBinding.inflate(...))
    else
        BotViewHolder(ItemChatBotBinding.inflate(...))
```

### 11.2 Speech Recognition — ActivityResultLauncher

```kotlin
// Không dùng startActivityForResult() (deprecated)
private val speechLauncher = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult()
) { result ->
    if (result.resultCode == RESULT_OK) {
        val text = result.data
            ?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            ?.firstOrNull()
        if (!text.isNullOrEmpty()) {
            addMessage(ChatMessage(text, true))
            viewModel.askAi(text)
        }
    }
}

binding.btnMic.setOnClickListener {
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, "vi-VN")
    }
    speechLauncher.launch(intent)
}
```

### 11.3 Disable Send button khi đang chờ AI

```kotlin
// Tránh spam request
viewModel.isLoading.observe(this) { loading ->
    binding.btnSend.isEnabled = !loading
}

viewModel.aiResponse.observe(this) { response ->
    binding.btnSend.isEnabled = true
    addMessage(ChatMessage(response?.reply ?: "Không có phản hồi", false))
}
```

---

## 12. Tầng UI — Hồ Sơ & Cài Đặt

### 12.1 `HoSoFragment.kt` — Profile display

```kotlin
// Load profile data khi fragment hiển thị
override fun onViewCreated(...) {
    val accountId = prefs.getString(AppPrefs.KEY_ACCOUNT_ID, "") ?: ""
    if (accountId.isNotEmpty()) {
        viewModel.loadProfile(accountId)
    }
}

// Reload mỗi khi quay lại fragment (sau khi edit)
override fun onResume() {
    super.onResume()
    viewModel.loadProfile(accountId)
}

// Phân quyền: chỉ hiện nút Admin nếu vai trò là ADMIN
val isAdmin = vaiTro in listOf("ADMIN", "QUẢN TRỊ HỆ THỐNG", "QUẢN TRỊ VIÊN")
binding.btnAdmin.visibility = if (isAdmin) View.VISIBLE else View.GONE
```

### 12.2 `EditProfileActivity.kt` — Prefill form

```kotlin
// Quan trọng: đọc đúng pref name/key
val accountId = getSharedPreferences(AppPrefs.PREF_MAIN, MODE_PRIVATE)
    .getString(AppPrefs.KEY_ACCOUNT_ID, "") ?: ""

// Load → disable form → fill data → enable form
setFormEnabled(false)
viewModel.loadProfile(accountId)

viewModel.userProfile.observe(this) { user ->
    binding.etName.setText(user?.ten ?: "")
    binding.etEmail.setText(user?.email ?: "")
    setFormEnabled(true)  // Mở khóa khi có data
}
```

### 12.3 Logout chuẩn

```kotlin
// Clear TẤT CẢ SharedPreferences
binding.btnLogout.setOnClickListener {
    getSharedPreferences(AppPrefs.PREF_MAIN, MODE_PRIVATE).edit().clear().apply()
    getSharedPreferences(AppPrefs.PREF_THEME, MODE_PRIVATE).edit().clear().apply()
    startActivity(Intent(this, LoginActivity::class.java))
    finish()
}
```

---

## 13. Hệ Thống Admin — Full CRUD

### 13.1 Template Pattern — `BaseAdminManageActivity<T : Any>`

```
Ý tưởng: Mỗi màn hình quản lý entity chỉ khác nhau ở:
  - Tiêu đề toolbar
  - Cách hiển thị item (title, subtitle)
  - Dialog Thêm/Sửa (fields khác nhau)
  - Hàm gọi ViewModel

→ Tạo abstract class xử lý phần chung:
  RecyclerView, FAB, Loading, Delete confirm dialog, BottomSheet helper
```

```kotlin
abstract class BaseAdminManageActivity<T : Any> : AppCompatActivity() {

    protected lateinit var binding: ActivityAdminManageBinding
    protected lateinit var viewModel: AdminViewModel
    protected lateinit var adapter: AdminManageAdapter<T>

    // Mỗi subclass chỉ cần override:
    abstract fun screenTitle(): String
    abstract fun loadList()
    abstract fun observeList()
    abstract fun getItemIcon(): String
    abstract fun mapItemToDisplay(item: T): Pair<String, String>
    abstract fun getItemId(item: T): String
    abstract fun showAddDialog()
    abstract fun showEditDialog(item: T)
    abstract fun performDelete(item: T)

    // Base xử lý: onCreate, setupRecyclerView, setupObservers, showDeleteConfirm
    // Helper: openBottomSheet(title, fields, onSave)
}
```

### 13.2 Subclass ví dụ — 50 dòng code cho 1 màn hình

```kotlin
class AdminTinTucManageActivity : BaseAdminManageActivity<TinTucItem>() {

    override fun screenTitle() = "Quản lý Tin Tức"
    override fun getItemIcon() = "📰"
    override fun loadList() = viewModel.fetchTinTuc()

    override fun observeList() {
        viewModel.tinTucList.observe(this) { list ->
            adapter.submitList(list)
            binding.layoutEmpty.visibility =
                if (list.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun mapItemToDisplay(item: TinTucItem) =
        Pair(item.tieuDe ?: "—", item.moTa?.take(60) ?: "")

    override fun getItemId(item: TinTucItem) = item.id ?: ""

    override fun showAddDialog() {
        openBottomSheet("➕ Thêm Tin Tức", listOf(
            Triple(0, "Tiêu đề", ""),
            Triple(1, "Mô tả ngắn", ""),
            Triple(2, "Nội dung đầy đủ", "")
        )) { values ->
            viewModel.addTinTuc(values[0]!!, values[1]!!, values[2]!!)
        }
    }

    override fun showEditDialog(item: TinTucItem) {
        openBottomSheet("✏️ Sửa Tin Tức", listOf(
            Triple(0, "Tiêu đề", item.tieuDe ?: ""),
            Triple(1, "Mô tả ngắn", item.moTa ?: ""),
            Triple(2, "Nội dung đầy đủ", item.noiDung ?: "")
        )) { values ->
            viewModel.updateTinTuc(item.id ?: "", values[0]!!, values[1]!!, values[2]!!)
        }
    }

    override fun performDelete(item: TinTucItem) =
        viewModel.deleteTinTuc(item.id ?: "")
}
```

### 13.3 Generic Adapter cho Admin

```kotlin
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
    // onBindViewHolder: gọi mapDisplay(item) để lấy title/subtitle
    // 2 buttons: Edit → onEdit(item), Delete → onDelete(item)
}
```

### 13.4 Dashboard 4 nhóm

```
Group 1: Nội dung      → Tin tức, Ngành học
Group 2: Tài chính     → Học phí, Quy trình, Học bổng
Group 3: Theo ngành    → Chỉ tiêu *, Nghề nghiệp *, Xét tuyển
Group 4: Hệ thống      → Thông tin trường (form edit), Tài khoản

* Chỉ tiêu & Nghề nghiệp: có thêm selector ngành
  → Bấm vào toolbar subtitle → AlertDialog chọn ngành → reload data
```

---

## 14. Các Kỹ Thuật Nâng Cao & Tối Ưu

### 14.1 ViewBinding — Không bao giờ dùng `findViewById`

```kotlin
// Trong Activity:
private lateinit var binding: ActivityMainBinding
binding = ActivityMainBinding.inflate(layoutInflater)
setContentView(binding.root)

// Trong Fragment — quan trọng: null binding khi destroy
private var _binding: FragmentHomeBinding? = null
private val binding get() = _binding!!

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null  // Tránh memory leak
}
```

### 14.2 Coroutines — Đúng Dispatcher

```kotlin
// IO thread cho network/disk
viewModelScope.launch(Dispatchers.IO) {
    val res = api.getData().awaitResponse()
    // postValue() thread-safe từ IO → Main
    _data.postValue(res.body())
}

// Main thread cho UI update
// LiveData.observe() tự chạy trên Main thread → không cần runOnUiThread
```

### 14.3 DiffUtil — Hiệu năng RecyclerView

```kotlin
// ❌ Tệ: update toàn bộ list
adapter.notifyDataSetChanged()

// ✅ Tốt: chỉ update item thay đổi
adapter.submitList(newList)
// DiffUtil tự diff background thread → dispatch update UI

// Require: areItemsTheSame (so ID) + areContentsTheSame (so value)
override fun areItemsTheSame(old: T, new: T) = old.id == new.id
override fun areContentsTheSame(old: T, new: T) = old == new
```

### 14.4 OkHttp Interceptor — Gắn Auth tự động

```kotlin
// Không cần truyền token thủ công vào mỗi API call
val authInterceptor = Interceptor { chain ->
    val token = prefs.getString(KEY_TOKEN, "") ?: ""
    val newRequest = chain.request().newBuilder()
        .addHeader("Authorization", "Bearer $token")
        .build()
    chain.proceed(newRequest)
}
```

### 14.5 FAB Snap — UX nâng cao

```kotlin
// Sau khi thả FAB → snap về cạnh gần nhất
ACTION_UP -> {
    val snapX = if (view.x + view.width/2 > screenWidth/2)
        screenWidth - view.width - margin
    else margin

    view.animate()
        .x(snapX)
        .setDuration(250)
        .setInterpolator(DecelerateInterpolator())
        .start()
}
```

### 14.6 SharedPreferences Pattern

```kotlin
// Lệnh đọc:
prefs.getString(AppPrefs.KEY_TOKEN, null)

// Lệnh ghi (không dùng commit() vì blocking):
prefs.edit().putString(AppPrefs.KEY_TOKEN, value).apply()

// Lệnh xóa hết (logout):
prefs.edit().clear().apply()
```

### 14.7 Offline-First Data Cache (SharedPreferences + Gson)

Ứng dụng chuyên nghiệp cần giữ lại dữ liệu cũ khi mất mạng thay vì hiển thị màn hình trắng lỗi.

```kotlin
// 1. Lưu Cache (LocalCache.kt)
fun saveTinTuc(context: Context, list: List<TinTucItem>) {
    val json = Gson().toJson(list)
    context.getSharedPreferences("haui_cache_v1", Context.MODE_PRIVATE)
        .edit().putString("cache_tintuc", json).apply()
}

// 2. Load Cache trong ViewModel NGAY LẬP TỨC 
fun loadData() {
    val context = HaUIApplication.appContext
    LocalCache.getTinTuc(context)?.let { _tinTucList.postValue(it) }

    viewModelScope.launch(Dispatchers.IO) { ... } // Gọi API background
}
```

### 14.8 UX Navigation — Không dùng Tab giả

> ❌ **Sai lầm UX phổ biến:** Bấm "Xem tất cả" tin tức → navigate người dùng sang một tab khác (Ngành Học — nav_news) làm gãy user flow.

```kotlin
// ✅ Chuẩn UX: Mở một Activity mới riêng biệt chứa toàn bộ list
binding.tvXemTatCa.setOnClickListener {
    startActivity(Intent(requireContext(), TinTucListActivity::class.java))
}
```

---

## 15. Git Workflow & Branching

### 15.1 Cấu trúc nhánh

```
main        ← Production code (stable, tested)
  │
  ├─ develop      ← Feature development
  │    └─ develop-fix  ← Bug fixes from develop
  │
  └─ release      ← Release candidate
```

### 15.2 Quy ước commit message (Conventional Commits)

```bash
feat(auth): Add JWT login with token persistence
fix(profile): Prefill edit form with logged-in user data
refactor(admin): Extract base class using Template Pattern
docs: Add comprehensive README.md
chore: Add OkHttp dependency for auth interceptor

# Format: <type>(<scope>): <description>
# Types: feat | fix | refactor | docs | chore | test | style
```

### 15.3 Luồng làm việc hàng ngày

```bash
# 1. Luôn pull trước khi code
git pull origin develop

# 2. Code → stage → commit
git add .
git commit -m "feat(nganh): Add search with accent removal"

# 3. Push lên nhánh hiện tại
git push origin develop

# 4. Khi feature xong → merge vào main
git checkout main
git merge develop
git push origin main

# 5. Đồng bộ tất cả nhánh
git push origin main:develop main:develop-fix main:release
```

---

## 16. Checklist Hoàn Thiện

### Layer 1: Project Setup
- [x] Tạo project với đúng package name
- [x] Cấu hình build.gradle.kts (dependencies, viewBinding, kotlin jvmToolchain)
- [x] Tạo package structure: network, repository, viewmodel, ui/*
- [x] Thêm `HaUIApplication` + đăng ký trong Manifest
- [x] Khởi tạo `ApiClient.init(this)` trong Application
- [x] Thêm `INTERNET` permission vào Manifest

### Layer 2: Network
- [x] `AppPrefs.kt` — constants tập trung
- [x] `ApiClient.kt` — OkHttp + Auth Interceptor + 2 instances (Core/AI)
- [x] `Models.kt` — Data classes đầy đủ cho tất cả entities
- [x] `ApiService.kt` — Full CRUD endpoints (GET/POST/PUT/DELETE)
- [x] `AiApiService.kt` — AI chat endpoint

### Layer 3: Repository
- [x] `AuthRepository` — login, register
- [x] `NganhRepository` / `NganhHocRepository` — ngành & chi tiết
- [x] `TruongRepository` — thông tin trường
- [x] `UserRepository` — người dùng
- [x] `ChatAiRepository` — AI service
- [x] `AdminRepository` — Full CRUD 9 entities
- [x] `InfoRepository` — thông tin thiết yếu

### Layer 4: ViewModel
- [x] `AuthViewModel` — login/register + isLoading + error
- [x] `HomeViewModel` — truong info + tin tuc list
- [x] `NganhViewModel` — nganh list + detail 5 tabs
- [x] `ProfileViewModel` — load + update + error handling
- [x] `ChatViewModel` — askAi + isLoading + error
- [x] `InfoViewModel` — thông tin thiết yếu
- [x] `AdminViewModel` — Full CRUD 9 entities + runAction helper

### Layer 5: UI
- [x] `LoginActivity` + `RegisterActivity` — Auth
- [x] `MainActivity` — BottomNav + FAB draggable snap
- [x] `HomeFragment` — Tin tức list + thông tin trường
- [x] `NganhHocFragment` — List ngành + search không dấu
- [x] `HoSoFragment` — Profile display + logout + dark mode
- [x] `NganhHocDetailActivity` — ViewPager2 5 tabs
- [x] `AiChatActivity` — Chat UI + speech recognition
- [x] `EditProfileActivity` — Form prefill + save
- [x] `ThongTinThietYeuActivity` — Thông tin thiết yếu

### Layer 6: Admin CRUD
- [x] `BaseAdminManageActivity<T>` — Template base class
- [x] `AdminManageAdapter<T>` — Generic adapter với DiffUtil
- [x] `AdminDashboardActivity` — 4 nhóm, 9 cards
- [x] `AdminNganhHocManageActivity`
- [x] `AdminTinTucManageActivity`
- [x] `AdminHocPhiManageActivity`
- [x] `AdminQuyTrinhManageActivity`
- [x] `AdminHocBongManageActivity`
- [x] `AdminChiTieuManageActivity` (có selector ngành)
- [x] `AdminNgheNghiepManageActivity` (có selector ngành)
- [x] `AdminPhuongThucManageActivity`
- [x] `AdminTruongManageActivity` (form đặc biệt)

### Layer 7: Shared Resources
- [x] `activity_admin_manage.xml` — layout dùng chung cho Admin screens
- [x] `dialog_add_edit_generic.xml` — BottomSheet dùng chung
- [x] `item_admin_manage.xml` — Row item dùng chung
- [x] Drawables: `ic_edit`, `ic_delete`, `ic_arrow_back`, `bg_icon_circle`
- [x] Colors, Strings, Themes đầy đủ

### Layer 8: Code Quality
- [x] Không dùng `startActivityForResult` (deprecated)
- [x] Không có `// Ignore error` trong catch block
- [x] SharedPreferences keys tập trung trong `AppPrefs`
- [x] `_binding = null` trong `onDestroyView`
- [x] `DiffUtil` cho tất cả adapter
- [x] Authorization header tự động qua OkHttp Interceptor
- [x] FAB snap về cạnh màn hình với animation
- [x] `Html.fromHtml` version-safe

### Layer 9: Documentation & Git
- [x] `README.md` đầy đủ với badges, API table, setup guide
- [x] `DEVELOPMENT_GUIDE.md` — file này
- [x] Conventional Commits xuyên suốt
- [x] 4 nhánh được đồng bộ: main, develop, develop-fix, release

---

> **💡 Tips cho người mới:** Luôn bắt đầu từ Layer 1 → 2 → 3... theo thứ tự. Đừng nhảy vào UI ngay khi chưa có Network layer. MVVM là dependency tree: UI cần ViewModel, ViewModel cần Repository, Repository cần ApiService.

---

*Last updated: 2026-04-19 | HaUI Tuyển Sinh v1.0 | codewithngoc*
