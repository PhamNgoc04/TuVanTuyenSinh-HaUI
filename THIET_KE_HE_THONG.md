# TAI LIEU THIET KE HE THONG - HAUI TUYEN SINH

**He thong:** Tu van Tuyen sinh Tu dong - Dai hoc Cong nghiep Ha Noi  
**Phien ban:** v1.0.0 | **Nam:** 2024  
**Tech Stack:** Ktor (Kotlin) + FastAPI (Python) + MySQL + Android (Kotlin + XML)

---

## 1. TONG QUAN KIEN TRUC HE THONG

### 1.1 Mo Hinh Tong The: Microservices

He thong duoc chia thanh 3 thanh phan doc lap:

```
+----------------------------------+
|     Android App (Frontend)       |
|   Kotlin + XML + Clean Arch      |
|   Retrofit / Gson                |
+----------+-------------+---------+
           |             |
     :8080 |             | :8000
           v             v
+------------------+  +------------------+
|  CORE API SERVER |  |  AI MICROSERVICE |
|  Ktor (Kotlin)   |  |  FastAPI (Python) |
|  JWT Auth        |  |  NLP Transformer  |
|  Ktorm ORM       |  |  Whisper STT      |
+--------+---------+  +------------------+
         |
         v
+------------------+
|  MySQL Database  |
|  hau_tuyensinh   |
|  14 Bang du lieu |
+------------------+
```

### 1.2 Ly Do Chon Microservices

| Tieu chi | Microservices (Hien tai) | Monolith (Cu) |
|----------|--------------------------|---------------|
| Tach biet AI | Core API va AI rieng biet | Dong chung Flask |
| Database | MySQL (ACID, scalable) | SQLite (yeu) |
| Bao mat | JWT + BCrypt | Thuong plain text |
| Mo rong | Scale tung service doc lap | Phai scale ca he thong |
| Hieu nang | Ktor non-blocking coroutines | Flask blocking |
| Bao tri | De cap nhat tung phan | Kho bao tri khi lon |

---

## 2. KIEN TRUC CORE API (KTOR KOTLIN)

### 2.1 Layered Architecture - 3 Tang

```
+------------------------------------------+
|  TANG 1: ROUTING (Controller Layer)      |
|  AuthRoutes.kt     - Dang nhap, Dang ky  |
|  TruongRoutes.kt   - Thong tin truong    |
|  NganhHocRoutes.kt - Chi tiet nganh hoc  |
|  HocPhiRoutes.kt   - Hoc phi, Hoc bong  |
|  NguoiDungRoutes.kt- Ho so nguoi dung   |
|  AdminRoutes.kt    - CRUD quan tri       |
+------------------------------------------+
|  TANG 2: ORM & ENTITY (Data Layer)       |
|  Ktorm Entity - Anh xa Object <-> Table  |
|  DatabaseManager  - Ket noi MySQL        |
|  13 Entity tuong ung 14 bang DB          |
+------------------------------------------+
|  TANG 3: DATABASE (Storage Layer)        |
|  MySQL 8.0 - hau_tuyensinh              |
|  14 bang, ~100 ban ghi seed data         |
+------------------------------------------+
```

### 2.2 Cac Mau Thiet Ke (Design Patterns) Dang Dung

**a) Singleton Pattern - DatabaseManager**
```kotlin
// Chi tao 1 ket noi DB duy nhat dung chung toan app
object DatabaseManager {
    val database = Database.connect(...)
}
```

**b) ORM Pattern - Ktorm Entity**
```kotlin
// Thay vi SQL tho, dung Entity de truy van
db.sequenceOf(NganhHocs).find { it.maNganh eq "7480201" }
```

**c) DTO Pattern - Data Transfer Object**
```kotlin
// Entity = Du lieu goc trong DB (co the nhieu truong)
interface NganhHoc : Entity<NganhHoc> { val maNganh; var tenNganh... }

// DTO = Chi gui nhung truong can thiet ve cho App
data class NganhHocDto(val maNganh, val tenNganh, val moTa, val coSo)
```

**d) RESTful API Pattern**
```
GET    /api/v1/nganh-hoc         -> Lay danh sach
GET    /api/v1/nganh-hoc/{id}    -> Lay 1 ban ghi
POST   /api/v1/admin/nganh-hoc   -> Tao moi
PUT    /api/v1/admin/nganh-hoc/{id} -> Cap nhat
DELETE /api/v1/admin/nganh-hoc/{id} -> Xoa
```

**e) Bearer Token Pattern (Bao mat)**
```
App -> POST /login -> Server tra ve JWT Token
App -> GET  /admin/... + Header: "Authorization: Bearer <token>"
Server kiem tra Token -> cho phep hoac tu choi
```

---

## 3. BAO MAT HE THONG

### 3.1 Ma Hoa Mat Khau: BCrypt
- Thuat toan: BCrypt voi salt rounds = 10
- Tinh chat: MA HOA MOT CHIEU (khong giai ma nguoc duoc)
- Trong DB chi luu: `$2a$10$xyz...` (chuoi hash)
- Khi dang nhap: BCrypt.checkpw(matKhauNhap, hashTrongDB)

### 3.2 Xac Thuc Phien: JWT (JSON Web Token)
- Thuat toan: HMAC-SHA512
- Payload: { username, role, expiredAt }
- Thoi han: 100 gio (~4 ngay)
- Luu tren App: SharedPreferences (Android)
- Format: `Authorization: Bearer eyJhbGci...`

### 3.3 Phan Quyen (Role-Based Access Control)
| Role | Quyen Han |
|------|-----------|
| USER | Xem thong tin, Chatbot, Doc tin tuc |
| ADMIN | Tat ca + CRUD nganh, tin tuc, hoc phi... |

---

## 4. CO SO DU LIEU MYSQL

### 4.1 Thong Tin Ket Noi
- **Host:** localhost:3306
- **Database:** hau_tuyensinh
- **Charset:** utf8mb4 (ho tro tieng Viet day du)
- **ORM:** Ktorm 3.6.0

### 4.2 So Do 14 Bang Du Lieu

```
Truong (1)
  |-- TinTuc (n)
  |-- QuyTrinhNhapHoc (n)

NganhHoc (n)
  |-- NganhNghe (n)       - nghe nghiep lien quan
  |-- ChiTieu (n)         - chi tieu tuyen sinh theo nam
  |-- TinChi (n)          - gia tin chi
  |-- YeuCauDauVao (n)    - diem xet tuyen, khoi thi
  |-- KhoaHoc (n-n) <---> ChuongTrinhHoc (n)
                              |-- HocPhi (n)
                              |-- HocBong (n)

TaiKhoan (1) <-- NguoiDung (1)
```

### 4.3 Chi Tiet Tung Bang

| STT | Bang | So Cot | So Dong | Chu Thich |
|-----|------|--------|---------|-----------|
| 1 | Truong | 6 | 1 | HaUI - 298 Cau Dien |
| 2 | NganhHoc | 4 | 10 | 10 nganh chinh |
| 3 | NganhNghe | 5 | 15 | Nghe + muc luong |
| 4 | ChiTieu | 5 | 14 | 2022/2023/2024 |
| 5 | TinChi | 3 | 12 | Gia tin chi moi nganh |
| 6 | YeuCauDauVao | 4 | 20 | Diem xet tuyen 20 khoi |
| 7 | ChuongTrinhHoc | 3 | 3 | Dai tra / CLC / Tien tien |
| 8 | HocPhi | 4 | 6 | Hoc phi 3 nam |
| 9 | HocBong | 5 | 5 | Samsung, KK, Chinh phu... |
| 10 | QuyTrinhNhapHoc | 3 | 5 | 5 buoc nhap hoc |
| 11 | TinTuc | 6 | 5 | Tin tuc tuyen sinh |
| 12 | TaiKhoan | 4 | 3 | admin + 2 sv |
| 13 | NguoiDung | 6 | 3 | Ho so nguoi dung |
| 14 | KhoaHoc | 3 | 8 | Bang trung gian N-N |

---

## 5. TOAN BO API ENDPOINTS (35 API)

### 5.1 Auth API (2)
| Method | URL | Mo ta | UC |
|--------|-----|-------|----|
| POST | /api/v1/auth/login | Dang nhap -> JWT Token | UC0 |
| POST | /api/v1/auth/register | Dang ky tai khoan | UC0 |

### 5.2 Truong & Tin Tuc (3)
| Method | URL | Mo ta | UC |
|--------|-----|-------|----|
| GET | /api/v1/truong | Thong tin truong HaUI | UC1 |
| GET | /api/v1/truong/tin-tuc | Danh sach tin tuc | UC6 |
| GET | /api/v1/truong/nganh-hoc | DS nganh qua truong | UC2 |

### 5.3 Nganh Hoc (6)
| Method | URL | Mo ta | UC |
|--------|-----|-------|----|
| GET | /api/v1/nganh-hoc | Danh sach tat ca nganh | UC2 |
| GET | /api/v1/nganh-hoc/{ma} | Chi tiet 1 nganh | UC2 |
| GET | /api/v1/nganh-hoc/{ma}/nghe-nghiep | Nghe nghiep lien quan | UC5 |
| GET | /api/v1/nganh-hoc/{ma}/chi-tieu | Chi tieu tuyen sinh | UC2 |
| GET | /api/v1/nganh-hoc/{ma}/yeu-cau-dau-vao | Khoi thi, diem | UC9 |
| GET | /api/v1/nganh-hoc/{ma}/tin-chi | Gia tin chi | UC8 |

### 5.4 Hoc Phi & Hoc Bong (4)
| Method | URL | Mo ta | UC |
|--------|-----|-------|----|
| GET | /api/v1/chuong-trinh-hoc | DS chuong trinh dao tao | UC8 |
| GET | /api/v1/hoc-phi | Thong tin hoc phi | UC8 |
| GET | /api/v1/hoc-bong | Danh sach hoc bong | UC4 |
| GET | /api/v1/quy-trinh-nhap-hoc | Cac buoc nhap hoc | UC7 |

### 5.5 Nguoi Dung (2)
| Method | URL | Mo ta | UC |
|--------|-----|-------|----|
| GET | /api/v1/nguoi-dung/{taiKhoanId} | Xem ho so | - |
| PUT | /api/v1/nguoi-dung/{id} | Cap nhat ho so | - |

### 5.6 Admin - CRUD (18)
| Method | URL | Mo ta | UC |
|--------|-----|-------|----|
| GET | /api/v1/admin/truong | Xem thong tin truong | UC10 |
| PUT | /api/v1/admin/truong/{ma} | Sua thong tin truong | UC10 |
| POST | /api/v1/admin/nganh-hoc | Them nganh hoc | UC11 |
| PUT | /api/v1/admin/nganh-hoc/{ma} | Sua nganh hoc | UC11 |
| DELETE | /api/v1/admin/nganh-hoc/{ma} | Xoa nganh hoc | UC11 |
| POST | /api/v1/admin/nghe-nghiep | Them nghe nghiep | UC12 |
| PUT | /api/v1/admin/nghe-nghiep/{ma} | Sua nghe nghiep | UC12 |
| DELETE | /api/v1/admin/nghe-nghiep/{ma} | Xoa nghe nghiep | UC12 |
| POST | /api/v1/admin/hoc-bong | Them hoc bong | UC13 |
| PUT | /api/v1/admin/hoc-bong/{id} | Sua hoc bong | UC13 |
| DELETE | /api/v1/admin/hoc-bong/{id} | Xoa hoc bong | UC13 |
| POST | /api/v1/admin/tin-tuc | Them tin tuc | UC14 |
| PUT | /api/v1/admin/tin-tuc/{id} | Sua tin tuc | UC14 |
| DELETE | /api/v1/admin/tin-tuc/{id} | Xoa tin tuc | UC14 |
| POST | /api/v1/admin/quy-trinh | Them buoc quy trinh | UC15 |
| PUT | /api/v1/admin/quy-trinh/{id} | Sua quy trinh | UC15 |
| POST | /api/v1/admin/hoc-phi | Them hoc phi | UC16 |
| PUT | /api/v1/admin/hoc-phi/{id} | Sua hoc phi | UC16 |
| POST | /api/v1/admin/yeu-cau-dau-vao | Them phuong thuc xet tuyen | UC17 |
| PUT | /api/v1/admin/yeu-cau-dau-vao/{id} | Sua diem xet tuyen | UC17 |
| GET | /api/v1/admin/tai-khoan | DS tai khoan nguoi dung | Admin |

### 5.7 AI Service - FastAPI Port 8000 (2)
| Method | URL | Mo ta | UC |
|--------|-----|-------|----|
| POST | /api/v1/ai/ask | Chatbot NLP tu van tuyen sinh | UC3 |
| POST | /api/v1/ai/voice | Nhan dang giong noi Whisper | UC3 |

---

## 6. KIEN TRUC AI MICROSERVICE (FASTAPI PYTHON)

### 6.1 Cong Nghe
- **Framework:** FastAPI + Uvicorn (port 8000)
- **NLP Model:** Transformer (Hugging Face) - Xu ly van ban tieng Viet
- **Speech-to-Text:** OpenAI Whisper - Chuyen giong noi thanh van ban
- **NLP Tokenizer:** Underthesea - Tach tu tieng Viet

### 6.2 Luong Xu Ly Chatbot
```
Nguoi dung noi ----> Android App
                        |
              [Whisper STT] (neu Voice Input)
                        |
                     Van ban
                        |
              [POST /api/v1/ai/ask]
                        |
            FastAPI Python NLP Service
                        |
              [Transformer Model]
                        |
              Phan tich y dinh cau hoi
                        |
              Tim kiem co so kien thuc
                        |
              Tra ve cau tra loi
                        |
                   Android App
```

---

## 7. FRONTEND ANDROID

### 7.1 Cong Nghe
- **Ngon ngu:** Kotlin
- **Giao dien:** XML + ViewBinding (DataBinding)
- **Kien truc:** Clean Architecture + MVVM
- **Dependency Injection:** Dagger-Hilt
- **Networking:** Retrofit 2 + Gson

### 7.2 Kien Truc Clean Architecture

```
+--------------------------------+
|  PRESENTATION LAYER            |
|  Activity / Fragment           |
|  ViewModel (LiveData)          |
|  XML Layout                    |
+--------------------------------+
|  DOMAIN LAYER                  |
|  Use Cases                     |
|  Repository Interface          |
+--------------------------------+
|  DATA LAYER                    |
|  Repository Implementation     |
|  ApiService (Retrofit)         |
|  ApiClient (Singleton)         |
+--------------------------------+
         |
         v (HTTP Request)
    Ktor Backend API
```

### 7.3 Cac Man Hinh Da Trien Khai
| Man Hinh | File | Mo ta |
|----------|------|-------|
| Dang nhap | LoginActivity.kt / activity_login.xml | JWT Auth |
| Trang chu | MainActivity.kt / activity_main.xml | Grid menu + Bot FA |
| Chi tiet truong | ThongTinTruongActivity.kt | Ket noi Ktor GET /truong |
| Chi tiet tin tuc | activity_tin_tuc.xml | Hien thi bai viet |

---

## 8. LUONG DU LIEU END-TO-END

### Vi du: Sinh vien xem diem xet tuyen CNTT

```
1. App -> GET /api/v1/nganh-hoc/7480201/yeu-cau-dau-vao
2. Ktor Router -> NganhHocRoutes.kt
3. Ktorm ORM -> SELECT * FROM YeuCauDauVao WHERE NganhHocmaNganh = '7480201'
4. MySQL -> Tra ve 3 ban ghi (A00, A01, D01)
5. Ktor -> Map sang DTO -> Serialize sang JSON
6. JSON Response -> App Android
7. Gson -> Parse JSON -> Data Class
8. ViewModel -> LiveData -> UI hien thi danh sach
```

---

## 9. SO SANH TRUOC VA SAU

| Tieu chi | He cu (Flask + SQLite) | He moi (Ktor + MySQL) |
|----------|------------------------|----------------------|
| Ngon ngu Backend | Python Flask | Kotlin Ktor |
| Co so du lieu | SQLite (1 file) | MySQL 8.0 (Production grade) |
| Kien truc | Monolith | Microservices |
| Bao mat | Khong co JWT, plain pass | JWT + BCrypt |
| AI | Dong chung voi API | Microservice rieng |
| Mo rong | Rat kho | De scale tung phan |
| ACID | Khong dam bao | Dam bao (MySQL) |
| So luong API | ~10 API | 35 API day du |
| So luong bang DB | 14 bang (co loi) | 14 bang (da kiem tra) |

---

## 10. DEPENDENCIES (LIBRARIES)

### Ktor Backend (build.gradle.kts)
```
ktor-server-core        - Loi cua Ktor
ktor-server-netty       - HTTP Server Netty
ktor-server-auth-jwt    - JWT Authentication
ktor-serialization-gson - JSON Serialization
ktorm-core              - ORM Framework
ktorm-support-mysql     - MySQL Dialect
mysql-connector-java    - MySQL Driver
jbcrypt                 - BCrypt Password Hashing
logback-classic         - Logging
```

### Android App (build.gradle.kts)
```
lifecycle-viewmodel-ktx  - ViewModel + Coroutines
lifecycle-livedata-ktx   - LiveData Observer
retrofit2                - HTTP Client
converter-gson           - JSON Parser
material                 - Material Design 3 UI
constraintlayout         - Layout linh hoat
ViewBinding              - Lien ket XML an toan
```

### AI Service (requirements.txt)
```
fastapi     - Python Web Framework
uvicorn     - ASGI Server
transformers- Hugging Face NLP Models
openai-whisper - Speech to Text
torch       - PyTorch ML Framework
```