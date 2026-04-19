<div align="center">

# 🎓 HaUI Tuyển Sinh — Android App

**Ứng dụng tư vấn tuyển sinh thông minh của Đại học Công nghiệp Hà Nội**

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-API_24+-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/)
[![MVVM](https://img.shields.io/badge/Architecture-MVVM-FF5252?style=for-the-badge)](https://developer.android.com/topic/architecture)
[![Retrofit](https://img.shields.io/badge/Network-Retrofit_2-48B983?style=for-the-badge)](https://square.github.io/retrofit/)

</div>

---

## 📱 Giới Thiệu

**HaUI Tuyển Sinh** là ứng dụng Android giúp thí sinh tra cứu thông tin tuyển sinh của **Trường Đại học Công nghiệp Hà Nội (HaUI)** một cách nhanh chóng, chính xác và tiện lợi. Ứng dụng tích hợp **Chatbot AI** để tư vấn theo thời gian thực và **Hệ thống Admin** để quản lý toàn bộ nội dung tuyển sinh.

### ✨ Điểm Nổi Bật
- 🤖 **AI Chatbot** — Tư vấn tuyển sinh theo thời gian thực bằng AI
- 🎓 **Tra cứu Ngành học** — Chi tiết chỉ tiêu, nghề nghiệp, học phí từng ngành
- 🏆 **Thông tin Học bổng** — Điều kiện và cách đăng ký học bổng
- ⚙️ **Admin Dashboard** — Quản lý toàn bộ hệ thống với Full CRUD
- 🔐 **Xác thực JWT** — Đăng nhập bảo mật phân quyền User/Admin

---

## 🏗️ Kiến Trúc Hệ Thống

```
HaUI Tuyển Sinh (Monorepo)
├── 📱 HaUI_TuyenSinh_Android/    ← Repo này (Android App)
├── ⚙️  Backend_Ktor/              ← REST API (Ktor + PostgreSQL)
└── 🤖 AI_FastAPI/                ← AI Chatbot Service (FastAPI + LLM)
```

### Kiến Trúc Android — MVVM + Repository Pattern

```
UI Layer          →  ViewModel Layer  →  Repository Layer  →  Network Layer
(Activities/      →  (AdminViewModel  →  (AdminRepository  →  (Retrofit API)
 Fragments)          NganhViewModel)     NganhHocRepo)
```

---

## 📂 Cấu Trúc Thư Mục

```
app/src/main/
├── java/com/codewithngoc/haui/tuyensinh/
│   ├── HaUIApplication.kt              # Application class
│   ├── network/
│   │   ├── ApiClient.kt                # Retrofit client (Core + AI)
│   │   ├── ApiService.kt               # Tất cả REST endpoints
│   │   └── Models.kt                   # Data classes (Request/Response)
│   ├── repository/
│   │   ├── AdminRepository.kt          # CRUD Admin operations
│   │   ├── AuthRepository.kt           # Login/Register
│   │   ├── NganhHocRepository.kt       # Ngành học queries
│   │   ├── TruongRepository.kt         # Thông tin trường
│   │   ├── InfoRepository.kt
│   │   ├── NganhRepository.kt
│   │   ├── UserRepository.kt
│   │   └── ChatAiRepository.kt         # AI Chat calls
│   ├── viewmodel/
│   │   ├── AdminViewModel.kt           # Full CRUD cho 9 entities
│   │   ├── AuthViewModel.kt
│   │   ├── NganhViewModel.kt
│   │   ├── HomeViewModel.kt
│   │   ├── InfoViewModel.kt
│   │   ├── ChatViewModel.kt
│   │   └── ProfileViewModel.kt
│   └── ui/
│       ├── admin/                      # 🔴 Admin Management (Full CRUD)
│       │   ├── AdminDashboardActivity.kt
│       │   ├── BaseAdminManageActivity.kt  # Abstract base (Template Pattern)
│       │   ├── AdminManageAdapter.kt       # Generic Adapter cho mọi entity
│       │   ├── AdminNganhHocManageActivity.kt
│       │   ├── AdminTinTucManageActivity.kt
│       │   ├── AdminHocPhiManageActivity.kt
│       │   ├── AdminQuyTrinhManageActivity.kt
│       │   ├── AdminHocBongManageActivity.kt
│       │   ├── AdminChiTieuManageActivity.kt
│       │   ├── AdminNgheNghiepManageActivity.kt
│       │   ├── AdminPhuongThucManageActivity.kt
│       │   └── AdminTruongManageActivity.kt
│       ├── auth/                       # Login / Register
│       ├── main/                       # MainActivity + Fragments (Home, NganhHoc, HoSo)
│       ├── course/                     # Chi tiết ngành học
│       ├── chat/                       # AI Chat screen
│       ├── profile/                    # Chỉnh sửa hồ sơ
│       └── info/                       # Thông tin thiết yếu
└── res/
    ├── layout/                         # 25+ XML layouts
    ├── drawable/                       # Vector icons & backgrounds
    └── values/                         # Colors, Strings, Themes
```

---

## 🔧 Công Nghệ Sử Dụng

| Thành phần | Công nghệ | Phiên bản |
|---|---|---|
| Ngôn ngữ | **Kotlin** | 1.9 |
| Min SDK | **Android** | API 24 (Android 7.0) |
| Target SDK | **Android** | API 36 |
| UI Binding | **ViewBinding** | built-in |
| Architecture | **MVVM** | AndroidX Architecture Components |
| Networking | **Retrofit 2** | 2.9.0 |
| JSON Parser | **Gson** | via Retrofit converter |
| Async | **Coroutines** + LiveData | 2.7.0 |
| UI Components | **Material Design 3** | latest |
| Navigation | **Fragment** + ViewPager2 | 1.6.2 |

---

## 🌐 Kết Nối Backend

Ứng dụng kết nối với **2 backend service** qua REST API:

### Core API (Ktor — Port 8080)
```kotlin
// Emulator: 10.0.2.2:8080
// Device thật: <IP_máy_tính>:8080
```

| Nhóm | Endpoint | Mô tả |
|---|---|---|
| Auth | `POST /api/v1/auth/login` | Đăng nhập |
| Auth | `POST /api/v1/auth/register` | Đăng ký |
| Trường | `GET /api/v1/truong` | Thông tin trường |
| Tin tức | `GET /api/v1/truong/tin-tuc` | Danh sách tin tức |
| Ngành học | `GET /api/v1/nganh-hoc` | Tất cả ngành học |
| Ngành học | `GET /api/v1/nganh-hoc/{ma}/chi-tieu` | Chỉ tiêu theo ngành |
| Ngành học | `GET /api/v1/nganh-hoc/{ma}/nghe-nghiep` | Nghề nghiệp theo ngành |
| Học phí | `GET /api/v1/hoc-phi` | Bảng học phí |
| Học bổng | `GET /api/v1/hoc-bong` | Thông tin học bổng |
| Quy trình | `GET /api/v1/quy-trinh-nhap-hoc` | Quy trình nhập học |
| Admin | `GET/POST/PUT/DELETE /api/v1/admin/**` | Quản trị hệ thống |

### AI Service (FastAPI — Port 8000)
```kotlin
// POST /api/v1/ai/ask  → Gửi câu hỏi, nhận tư vấn AI
```

---

## ⚙️ Hệ Thống Admin — Full CRUD Management

Admin Dashboard cung cấp quản lý đầy đủ **(Xem · Thêm · Sửa · Xóa)** cho **9 entities**:

| Entity | Activity | Đặc điểm |
|---|---|---|
| 🎓 Ngành Học | `AdminNganhHocManageActivity` | CRUD cơ bản |
| 📰 Tin Tức | `AdminTinTucManageActivity` | CRUD cơ bản |
| 💰 Học Phí | `AdminHocPhiManageActivity` | Format tiền tệ VNĐ |
| 📋 Quy Trình | `AdminQuyTrinhManageActivity` | CRUD từng bước quy trình |
| 🏆 Học Bổng | `AdminHocBongManageActivity` | CRUD cơ bản |
| 📊 Chỉ Tiêu | `AdminChiTieuManageActivity` | Lọc theo ngành, đổi ngành khi xem |
| 💼 Nghề Nghiệp | `AdminNgheNghiepManageActivity` | Lọc theo ngành, đổi ngành khi xem |
| 📝 Phương thức Xét tuyển | `AdminPhuongThucManageActivity` | CRUD cơ bản |
| 🏫 Thông Tin Trường | `AdminTruongManageActivity` | Form chỉnh sửa trực tiếp |

### Kiến Trúc Admin — Template Pattern

```kotlin
// BaseAdminManageActivity<T : Any> — Abstract base class
// Mỗi entity Activity chỉ cần override ~5 methods:
override fun screenTitle(): String    // Tiêu đề toolbar
override fun loadList()               // Gọi fetch API
override fun observeList()            // Observe LiveData
override fun mapItemToDisplay()       // Pair<title, subtitle>
override fun showAddDialog()          // BottomSheet thêm mới
override fun showEditDialog(item)     // BottomSheet sửa
override fun performDelete(item)      // Gọi delete API
```

---

## 👤 Tài Khoản & Phân Quyền

| Role | Quyền truy cập |
|---|---|
| **USER** | Xem thông tin, Chatbot AI, Chỉnh sửa hồ sơ |
| **ADMIN** | Tất cả USER + Quản trị hệ thống (Full CRUD) |

---

## 🚀 Hướng Dẫn Cài Đặt & Chạy

### Yêu Cầu
- **Android Studio** Hedgehog 2023.1 trở lên
- **JDK 17**
- **Gradle 8.x**
- Backend Ktor đang chạy ở port **8080**
- _(Tùy chọn)_ AI FastAPI đang chạy ở port **8000**

### Các Bước

**1. Clone repo:**
```bash
git clone https://github.com/PhamNgoc04/TuVanTuyenSinh-HaUI.git
cd TuVanTuyenSinh-HaUI/HaUI_TuyenSinh_Android
```

**2. Cấu hình địa chỉ backend** trong `ApiClient.kt`:
```kotlin
// Chạy Emulator:
private const val CORE_BASE_URL = "http://10.0.2.2:8080"

// Chạy trên điện thoại thật:
private const val CORE_BASE_URL = "http://192.168.x.x:8080"  // IP máy tính
```

**3. Mở project trong Android Studio** → Sync Gradle → Run ▶️

---

## 📊 Sơ Đồ Màn Hình

```
LoginActivity
    │
    ├──► MainActivity (User)
    │       ├── HomeFragment       — Tin tức, giới thiệu trường
    │       ├── NganhHocFragment   — Danh sách & chi tiết ngành học
    │       └── HoSoFragment       — Hồ sơ người dùng
    │
    ├──► AdminDashboardActivity (Admin)
    │       ├── AdminNganhHocManageActivity
    │       ├── AdminTinTucManageActivity
    │       ├── AdminHocPhiManageActivity
    │       ├── AdminQuyTrinhManageActivity
    │       ├── AdminHocBongManageActivity
    │       ├── AdminChiTieuManageActivity     ← Lọc theo ngành
    │       ├── AdminNgheNghiepManageActivity  ← Lọc theo ngành
    │       ├── AdminPhuongThucManageActivity
    │       └── AdminTruongManageActivity      ← Form chỉnh sửa
    │
    └──► AiChatActivity — Chatbot tư vấn tuyển sinh
```

---

## 🌿 Quy Trình Git

| Nhánh | Mục đích |
|---|---|
| `main` | Production — code ổn định, đã kiểm tra |
| `develop` | Development — tính năng mới đang phát triển |
| `develop-fix` | Bugfix từ develop |
| `release` | Chuẩn bị phát hành |

---

## 📋 Roadmap

- [x] Đăng nhập / Đăng ký với JWT
- [x] Xem thông tin ngành học, học phí, học bổng
- [x] Chi tiết ngành học (chỉ tiêu, nghề nghiệp, tín chỉ)
- [x] AI Chatbot tư vấn tuyển sinh
- [x] Admin: Thêm Ngành Học, Tin Tức
- [x] **Admin Full CRUD** cho 9 entities (Ngành, Tin tức, Học phí, Quy trình, Học bổng, Chỉ tiêu, Nghề nghiệp, Xét tuyển, Thông tin trường)
- [ ] Quản lý Tài Khoản người dùng (Admin)
- [ ] Thông báo đẩy (Push Notification)
- [ ] Nộp hồ sơ tuyển sinh trực tuyến
- [ ] Dark Mode hoàn thiện

---

## 👨‍💻 Tác Giả

**Phạm Ngọc** — *codewithngoc*

> Dự án **Đồ Án Chuyên Ngành** — Hệ thống Tư Vấn Tuyển Sinh HaUI  
> Trường Đại học Công nghiệp Hà Nội

---

<div align="center">

Made with ❤️ by **codewithngoc** · HaUI · 2024

</div>
