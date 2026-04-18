# HaUI Core API - Backend Documentation

> **Du an:** He thong Tu van Tuyen sinh Tu dong - Dai hoc Cong nghiep Ha Noi  
> **Cong nghe:** Ktor (Kotlin) + MySQL + Ktorm ORM  
> **Port:** `localhost:8080`  
> **Phien ban:** v1.0.0

---

## 1. Cau Truc Thu Muc

```
HaUI_Core_Ktor/
├── build.gradle.kts              # Cau hinh Gradle + Dependencies
├── settings.gradle.kts           # Ten project
├── init_db.sql                   # Script tao 14 bang + Seed Data
└── src/main/kotlin/com/haui/
    ├── Application.kt            # Diem khoi dong Server Netty (port 8080)
    ├── auth/
    │   ├── JwtConfig.kt          # Cau hinh ma hoa JWT Token
    │   └── AuthModels.kt         # DTO: AuthRequest, AuthResponse
    ├── database/
    │   ├── DatabaseManager.kt    # Ket noi MySQL qua Ktorm
    │   └── entities/
    │       ├── TruongEntity.kt
    │       ├── TaiKhoanEntity.kt
    │       ├── NguoiDungEntity.kt
    │       ├── NganhHocEntity.kt
    │       ├── NganhNgheEntity.kt
    │       ├── ChiTieuEntity.kt
    │       ├── TinChiEntity.kt
    │       ├── YeuCauDauVaoEntity.kt
    │       ├── ChuongTrinhHocEntity.kt
    │       ├── HocPhiEntity.kt
    │       ├── HocBongEntity.kt
    │       ├── QuyTrinhNhapHocEntity.kt
    │       └── TinTucEntity.kt
    └── routing/
        ├── AuthRoutes.kt         # Dang nhap, Dang ky
        ├── TruongRoutes.kt       # Thong tin truong, Tin tuc, Nganh hoc
        ├── NganhHocRoutes.kt     # Chi tiet nganh, nghe nghiep, chi tieu...
        ├── HocPhiRoutes.kt       # Hoc phi, Hoc bong, Quy trinh nhap hoc
        ├── NguoiDungRoutes.kt    # Ho so nguoi dung
        └── AdminRoutes.kt        # CRUD toan bo (Admin only)
```

---

## 2. Cai Dat & Chay Server

### Yeu cau he thong
- Java JDK 17+
- IntelliJ IDEA (khuyen nghi)
- MySQL 8.0+ (XAMPP hoac MySQL Workbench)

### Buoc 1: Chay Database
```sql
-- Mo MySQL Workbench, copy paste va chay file:
D:\Mobile_Kotlin_DevPro\DACK\HaUI_Core_Ktor\init_db.sql
```

### Buoc 2: Cau hinh ket noi
Mo file `DatabaseManager.kt` va chinh sua:
```kotlin
val database = Database.connect(
    url = "jdbc:mysql://localhost:3306/hau_tuyensinh",
    user = "root",
    password = "MAT_KHAU_MYSQL_CUA_BAN"  // <-- Sua o day
)
```

### Buoc 3: Chay Ktor Server
```bash
./gradlew run
# Hoac bam nut Run (tam giac xanh) trong IntelliJ IDEA
```
Server se chay tai: `http://localhost:8080`

---

## 3. Toan Bo API Endpoints (20 Chuc Nang)

### AUTH - Xac Thuc Nguoi Dung
| Method | Endpoint | Mo ta | UC |
|--------|----------|-------|----|
| POST | `/api/v1/auth/login` | Dang nhap - tra ve JWT Token | UC0 |
| POST | `/api/v1/auth/register` | Dang ky tai khoan moi | UC0 |

**Request Body (Login):**
```json
{ "username": "admin", "password": "P@ssw0rd123" }
```
**Response (Login):**
```json
{ "status": "SUCCESS", "token": "eyJhbGci...", "role": "ADMIN", "message": "Dang nhap thanh cong!" }
```

---

### TRUONG - Thong Tin Truong & Tin Tuc
| Method | Endpoint | Mo ta | UC |
|--------|----------|-------|----|
| GET | `/api/v1/truong` | Lay thong tin truong HaUI | UC1 |
| GET | `/api/v1/truong/tin-tuc` | Danh sach tin tuc tuyen sinh | UC6 |
| GET | `/api/v1/truong/nganh-hoc` | Danh sach tat ca nganh hoc | UC2 |

---

### NGANH HOC - Chi Tiet Theo Nganh
| Method | Endpoint | Mo ta | UC |
|--------|----------|-------|----|
| GET | `/api/v1/nganh-hoc` | Danh sach tat ca nganh | UC2 |
| GET | `/api/v1/nganh-hoc/{maNganh}` | Chi tiet 1 nganh hoc | UC2 |
| GET | `/api/v1/nganh-hoc/{maNganh}/nghe-nghiep` | Nghe nghiep lien quan | UC5 |
| GET | `/api/v1/nganh-hoc/{maNganh}/chi-tieu` | Chi tieu tuyen sinh theo nam | UC2 |
| GET | `/api/v1/nganh-hoc/{maNganh}/yeu-cau-dau-vao` | Diem xet tuyen, khoi thi | UC9 |
| GET | `/api/v1/nganh-hoc/{maNganh}/tin-chi` | Gia tin chi cua nganh | UC8 |

---

### HOC PHI & HOC BONG
| Method | Endpoint | Mo ta | UC |
|--------|----------|-------|----|
| GET | `/api/v1/chuong-trinh-hoc` | DS chuong trinh dao tao | UC8 |
| GET | `/api/v1/hoc-phi` | Thong tin hoc phi theo nam hoc | UC8 |
| GET | `/api/v1/hoc-bong` | Danh sach hoc bong | UC4 |
| GET | `/api/v1/quy-trinh-nhap-hoc` | Cac buoc quy trinh nhap hoc | UC7 |

---

### NGUOI DUNG - Ho So Ca Nhan
| Method | Endpoint | Mo ta | UC |
|--------|----------|-------|----|
| GET | `/api/v1/nguoi-dung/{taiKhoanId}` | Xem ho so nguoi dung | - |
| PUT | `/api/v1/nguoi-dung/{id}` | Cap nhat thong tin ho so | - |

---

### ADMIN - Quan Ly He Thong (Admin Only)
| Method | Endpoint | Mo ta | UC |
|--------|----------|-------|----|
| GET | `/api/v1/admin/truong` | Xem thong tin truong | UC10 |
| PUT | `/api/v1/admin/truong/{ma}` | Chinh sua thong tin truong | UC10 |
| POST | `/api/v1/admin/nganh-hoc` | Them nganh hoc moi | UC11 |
| PUT | `/api/v1/admin/nganh-hoc/{ma}` | Sua thong tin nganh | UC11 |
| DELETE | `/api/v1/admin/nganh-hoc/{ma}` | Xoa nganh hoc | UC11 |
| POST | `/api/v1/admin/nghe-nghiep` | Them nghe nghiep | UC12 |
| PUT | `/api/v1/admin/nghe-nghiep/{ma}` | Sua dinh huong nghe nghiep | UC12 |
| DELETE | `/api/v1/admin/nghe-nghiep/{ma}` | Xoa nghe nghiep | UC12 |
| POST | `/api/v1/admin/hoc-bong` | Them hoc bong | UC13 |
| PUT | `/api/v1/admin/hoc-bong/{id}` | Sua thong tin hoc bong | UC13 |
| DELETE | `/api/v1/admin/hoc-bong/{id}` | Xoa hoc bong | UC13 |
| POST | `/api/v1/admin/tin-tuc` | Them tin tuc moi | UC14 |
| PUT | `/api/v1/admin/tin-tuc/{id}` | Sua tin tuc | UC14 |
| DELETE | `/api/v1/admin/tin-tuc/{id}` | Xoa tin tuc | UC14 |
| POST | `/api/v1/admin/quy-trinh` | Them buoc quy trinh | UC15 |
| PUT | `/api/v1/admin/quy-trinh/{id}` | Sua quy trinh nhap hoc | UC15 |
| DELETE | `/api/v1/admin/quy-trinh/{id}` | Xoa buoc quy trinh | UC15 |
| POST | `/api/v1/admin/hoc-phi` | Them thong tin hoc phi | UC16 |
| PUT | `/api/v1/admin/hoc-phi/{id}` | Chinh sua hoc phi | UC16 |
| DELETE | `/api/v1/admin/hoc-phi/{id}` | Xoa hoc phi | UC16 |
| POST | `/api/v1/admin/yeu-cau-dau-vao` | Them phuong thuc xet tuyen | UC17 |
| PUT | `/api/v1/admin/yeu-cau-dau-vao/{id}` | Chinh sua diem xet tuyen | UC17 |
| DELETE | `/api/v1/admin/yeu-cau-dau-vao/{id}` | Xoa yeu cau dau vao | UC17 |
| GET | `/api/v1/admin/tai-khoan` | Danh sach nguoi dung he thong | UC - Admin |

---

## 4. Co So Du Lieu MySQL

**Database:** `hau_tuyensinh`  
**14 Bang du lieu:**

| Bang | So ban ghi | Mo ta |
|------|------------|-------|
| Truong | 1 | Thong tin truong HaUI |
| NganhHoc | 10 | 10 nganh hoc chinh |
| NganhNghe | 15 | 15 nghe nghiep lien quan |
| YeuCauDauVao | 20 | Diem xet tuyen 20 khoi |
| ChiTieu | 14 | Chi tieu 2022/2023/2024 |
| TinChi | 12 | Gia tin chi theo nganh |
| ChuongTrinhHoc | 3 | Dai tra, CLC, Tien tien |
| HocPhi | 6 | Hoc phi 3 nam học |
| HocBong | 5 | 5 loai hoc bong |
| QuyTrinhNhapHoc | 5 | 5 buoc nhap hoc |
| TinTuc | 5 | 5 bai tin tuc tuyen sinh |
| TaiKhoan | 3 | admin + 2 sinh vien |
| NguoiDung | 3 | Ho so 3 nguoi dung |
| KhoaHoc | 8 | Bang trung gian N-N |

---

## 5. Bao Mat

- **Ma hoa mat khau:** BCrypt (salt rounds = 10)
- **Xac thuc phien:** JWT Bearer Token (thoi han 100 gio)
- **Phan quyen:** ADMIN / USER role-based
- **Luu Token phia App:** SharedPreferences (Android)

---

## 6. Ket Noi Voi Frontend Android

App Android goi API Backend qua Retrofit:

```kotlin
// ApiClient.kt
private const val CORE_BASE_URL = "http://10.0.2.2:8080"
// 10.0.2.2 = localhost cua may tinh khi chay tren Emulator Android
// Neu dung dien thoai that: doi thanh IP LAN (vi du: http://192.168.1.10:8080)
```

---

## 7. Ket Noi Voi AI Service (Python FastAPI)

AI Service chay tai: `http://localhost:8000`

| Method | Endpoint | Mo ta |
|--------|----------|-------|
| POST | `/api/v1/ai/ask` | Gui cau hoi - nhan tu van NLP Simulator |
| POST | `/api/v1/ai/voice` | Gui audio - nhan van ban (Whisper mock) |

**Request:**
```json
{ "text": "Nganh CNTT diem chuan bao nhieu?" }
```
**Response:**
```json
{ "question": "...", "reply": "Diem chuan CNTT 2023 la 24.5 diem..." }
```