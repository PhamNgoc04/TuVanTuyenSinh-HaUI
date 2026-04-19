package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.ApiClient

class AdminRepository {
    private val api = ApiClient.instance

    fun getTaiKhoan() = api.getTaiKhoanAdmin()

    // ===== Nganh Hoc =====
    fun addNganhHoc(body: Map<String, String>) = api.addNganhHocAdmin(body)
    fun updateNganhHoc(ma: String, body: Map<String, String>) = api.updateNganhHocAdmin(ma, body)
    fun deleteNganhHoc(ma: String) = api.deleteNganhHocAdmin(ma)

    // ===== Tin Tuc =====
    fun addTinTuc(body: Map<String, String>) = api.addTinTucAdmin(body)
    fun updateTinTuc(id: String, body: Map<String, String>) = api.updateTinTucAdmin(id, body)
    fun deleteTinTuc(id: String) = api.deleteTinTucAdmin(id)

    // ===== Truong =====
    fun updateTruong(ma: String, body: Map<String, String>) = api.updateTruongAdmin(ma, body)

    // ===== Hoc Phi =====
    fun addHocPhi(body: Map<String, String>) = api.addHocPhiAdmin(body)
    fun updateHocPhi(id: String, body: Map<String, String>) = api.updateHocPhiAdmin(id, body)
    fun deleteHocPhi(id: String) = api.deleteHocPhiAdmin(id)

    // ===== Quy Trinh =====
    fun addQuyTrinh(body: Map<String, String>) = api.addQuyTrinhAdmin(body)
    fun updateQuyTrinh(id: String, body: Map<String, String>) = api.updateQuyTrinhAdmin(id, body)
    fun deleteQuyTrinh(id: String) = api.deleteQuyTrinhAdmin(id)

    // ===== Hoc Bong =====
    fun addHocBong(body: Map<String, String>) = api.addHocBongAdmin(body)
    fun updateHocBong(id: String, body: Map<String, String>) = api.updateHocBongAdmin(id, body)
    fun deleteHocBong(id: String) = api.deleteHocBongAdmin(id)

    // ===== Chi Tieu =====
    fun addChiTieu(body: Map<String, String>) = api.addChiTieuAdmin(body)
    fun updateChiTieu(id: String, body: Map<String, String>) = api.updateChiTieuAdmin(id, body)
    fun deleteChiTieu(id: String) = api.deleteChiTieuAdmin(id)

    // ===== Nghe Nghiep =====
    fun addNgheNghiep(body: Map<String, String>) = api.addNgheNghiepAdmin(body)
    fun updateNgheNghiep(id: String, body: Map<String, String>) = api.updateNgheNghiepAdmin(id, body)
    fun deleteNgheNghiep(id: String) = api.deleteNgheNghiepAdmin(id)

    // ===== Phuong Thuc Xet Tuyen =====
    fun getPhuongThuc() = api.getPhuongThucXetTuyen()
    fun addPhuongThuc(body: Map<String, String>) = api.addPhuongThucAdmin(body)
    fun updatePhuongThuc(id: String, body: Map<String, String>) = api.updatePhuongThucAdmin(id, body)
    fun deletePhuongThuc(id: String) = api.deletePhuongThucAdmin(id)
}
