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

    // ===== ADMIN =====
    @GET("/api/v1/admin/tai-khoan")
    fun getTaiKhoanAdmin(): Call<AdminTaiKhoanResponse>

    // --- Nganh Hoc ---
    @POST("/api/v1/admin/nganh-hoc")
    fun addNganhHocAdmin(@Body body: Map<String, String>): Call<GenericResponse>

    @PUT("/api/v1/admin/nganh-hoc/{ma}")
    fun updateNganhHocAdmin(@Path("ma") ma: String, @Body body: Map<String, String>): Call<GenericResponse>

    @DELETE("/api/v1/admin/nganh-hoc/{ma}")
    fun deleteNganhHocAdmin(@Path("ma") ma: String): Call<GenericResponse>

    // --- Tin Tuc ---
    @POST("/api/v1/admin/tin-tuc")
    fun addTinTucAdmin(@Body body: Map<String, String>): Call<GenericResponse>

    @PUT("/api/v1/admin/tin-tuc/{id}")
    fun updateTinTucAdmin(@Path("id") id: String, @Body body: Map<String, String>): Call<GenericResponse>

    @DELETE("/api/v1/admin/tin-tuc/{id}")
    fun deleteTinTucAdmin(@Path("id") id: String): Call<GenericResponse>

    // --- Truong ---
    @PUT("/api/v1/admin/truong/{ma}")
    fun updateTruongAdmin(@Path("ma") ma: String, @Body body: Map<String, String>): Call<GenericResponse>

    // --- Hoc Phi ---
    @POST("/api/v1/admin/hoc-phi")
    fun addHocPhiAdmin(@Body body: Map<String, String>): Call<GenericResponse>

    @PUT("/api/v1/admin/hoc-phi/{id}")
    fun updateHocPhiAdmin(@Path("id") id: String, @Body body: Map<String, String>): Call<GenericResponse>

    @DELETE("/api/v1/admin/hoc-phi/{id}")
    fun deleteHocPhiAdmin(@Path("id") id: String): Call<GenericResponse>

    // --- Quy Trinh ---
    @POST("/api/v1/admin/quy-trinh")
    fun addQuyTrinhAdmin(@Body body: Map<String, String>): Call<GenericResponse>

    @PUT("/api/v1/admin/quy-trinh/{id}")
    fun updateQuyTrinhAdmin(@Path("id") id: String, @Body body: Map<String, String>): Call<GenericResponse>

    @DELETE("/api/v1/admin/quy-trinh/{id}")
    fun deleteQuyTrinhAdmin(@Path("id") id: String): Call<GenericResponse>

    // --- Hoc Bong ---
    @POST("/api/v1/admin/hoc-bong")
    fun addHocBongAdmin(@Body body: Map<String, String>): Call<GenericResponse>

    @PUT("/api/v1/admin/hoc-bong/{id}")
    fun updateHocBongAdmin(@Path("id") id: String, @Body body: Map<String, String>): Call<GenericResponse>

    @DELETE("/api/v1/admin/hoc-bong/{id}")
    fun deleteHocBongAdmin(@Path("id") id: String): Call<GenericResponse>

    // --- Chi Tieu (nested under nganh) ---
    @POST("/api/v1/admin/chi-tieu")
    fun addChiTieuAdmin(@Body body: Map<String, String>): Call<GenericResponse>

    @PUT("/api/v1/admin/chi-tieu/{id}")
    fun updateChiTieuAdmin(@Path("id") id: String, @Body body: Map<String, String>): Call<GenericResponse>

    @DELETE("/api/v1/admin/chi-tieu/{id}")
    fun deleteChiTieuAdmin(@Path("id") id: String): Call<GenericResponse>

    // --- Nghe Nghiep ---
    @POST("/api/v1/admin/nghe-nghiep")
    fun addNgheNghiepAdmin(@Body body: Map<String, String>): Call<GenericResponse>

    @PUT("/api/v1/admin/nghe-nghiep/{id}")
    fun updateNgheNghiepAdmin(@Path("id") id: String, @Body body: Map<String, String>): Call<GenericResponse>

    @DELETE("/api/v1/admin/nghe-nghiep/{id}")
    fun deleteNgheNghiepAdmin(@Path("id") id: String): Call<GenericResponse>

    // --- Phuong Thuc Xet Tuyen ---
    @GET("/api/v1/phuong-thuc-xet-tuyen")
    fun getPhuongThucXetTuyen(): Call<PhuongThucListResponse>

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

