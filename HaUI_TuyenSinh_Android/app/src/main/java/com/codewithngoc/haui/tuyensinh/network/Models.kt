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

data class GenericResponse(
    val status: String,
    val message: String?
)

// ===== TRUONG =====
data class TruongItem(
    val maTruong: String?,
    val tenTruong: String?,
    val moTa: String?,
    val thanhPho: String?,
    val quan: String?,
    val duong: String?
)
data class TruongListResponse(val status: String, val data: List<TruongItem>)

// ===== TIN TUC =====
data class TinTucItem(
    val id: String?,
    val tieuDe: String?,
    val anh: String?,
    val moTa: String?,
    val noiDung: String?
)
data class TinTucListResponse(val status: String, val data: List<TinTucItem>)

// ===== NGANH HOC =====
data class NganhHocItem(
    val maNganh: String?,
    val tenNganh: String?,
    val moTa: String?,
    val coSo: String?
)
data class NganhHocListResponse(val status: String, val data: List<NganhHocItem>)

// ===== NGHE NGHIEP =====
data class NgheNghiepItem(
    val maNghe: String?,
    val tenNghe: String?,
    val mucLuong: String?,
    val tinhTrang: String?
)
data class NgheNghiepListResponse(val status: String, val data: List<NgheNghiepItem>)

// ===== CHI TIEU =====
data class ChiTieuItem(
    val id: String?,          // ✅ Bug #2 Fix: thêm id để CRUD đúng endpoint
    val nam: String?,
    val soLuong: String?,
    val phuongThuc: String?
)
data class ChiTieuListResponse(val status: String, val data: List<ChiTieuItem>)

// ===== YEU CAU DAU VAO =====
data class YeuCauDauVaoItem(
    val khoi: String?,
    val diemTong: String?
)
data class YeuCauDauVaoListResponse(val status: String, val data: List<YeuCauDauVaoItem>)

// ===== TIN CHI =====
data class TinChiItem(val id: String?, val giaTien: String?)
data class TinChiListResponse(val status: String, val data: List<TinChiItem>)

// ===== HOC PHI =====
data class HocPhiItem(
    val id: String?,
    val soTien: String?,
    val namHoc: String?,
    val chuongTrinhHocId: String?
)
data class HocPhiListResponse(val status: String, val data: List<HocPhiItem>)

// ===== HOC BONG =====
data class HocBongItem(
    val id: String?,
    val loaiHb: String?,
    val diemYc: String?,
    val hanhKiemYc: String?
)
data class HocBongListResponse(val status: String, val data: List<HocBongItem>)

// ===== QUY TRINH NHAP HOC =====
data class QuyTrinhItem(val id: String?, val noiDung: String?)
data class QuyTrinhListResponse(val status: String, val data: List<QuyTrinhItem>)

// ===== CHUONG TRINH HOC =====
data class ChuongTrinhHocItem(
    val id: String?,
    val tenChuongTrinh: String?,
    val moTa: String?
)
data class ChuongTrinhHocListResponse(val status: String, val data: List<ChuongTrinhHocItem>)

// ===== NGUOI DUNG =====
data class NguoiDungResponse(
    val id: String?,
    val ten: String?,
    val avatar: String?,
    val email: String?,
    val vaiTro: String?
)

// ===== PHUONG THUC XET TUYEN =====
data class PhuongThucItem(
    val id: String?,
    val tenPhuongThuc: String?,
    val moTa: String?
)
data class PhuongThucListResponse(val status: String, val data: List<PhuongThucItem>)

// ===== ADMIN =====
data class TaiKhoanAdminItem(val id: String?, val username: String?, val role: String?)
data class AdminTaiKhoanResponse(val status: String, val data: List<TaiKhoanAdminItem>)

// ===== AI SERVICE =====
data class AiChatRequest(val text: String)
data class AiChatResponse(
    val question: String?,
    val reply: String?
)
