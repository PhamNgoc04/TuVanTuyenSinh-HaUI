package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.ApiClient

class AdminRepository {
    fun getTaiKhoan() = ApiClient.instance.getTaiKhoanAdmin()

    // Nganh Hoc
    fun addNganhHoc(body: Map<String, String>) = ApiClient.instance.addNganhHocAdmin(body)
    fun updateNganhHoc(ma: String, body: Map<String, String>) = ApiClient.instance.updateNganhHocAdmin(ma, body)
    fun deleteNganhHoc(ma: String) = ApiClient.instance.deleteNganhHocAdmin(ma)

    // Tin Tuc
    fun addTinTuc(body: Map<String, String>) = ApiClient.instance.addTinTucAdmin(body)
    fun updateTinTuc(id: String, body: Map<String, String>) = ApiClient.instance.updateTinTucAdmin(id, body)
    fun deleteTinTuc(id: String) = ApiClient.instance.deleteTinTucAdmin(id)

    // Truong
    fun updateTruong(ma: String, body: Map<String, String>) = ApiClient.instance.updateTruongAdmin(ma, body)

    // Hoc Phi
    fun addHocPhi(body: Map<String, String>) = ApiClient.instance.addHocPhiAdmin(body)
    fun updateHocPhi(id: String, body: Map<String, String>) = ApiClient.instance.updateHocPhiAdmin(id, body)

    // Quy Trinh
    fun addQuyTrinh(body: Map<String, String>) = ApiClient.instance.addQuyTrinhAdmin(body)
    fun updateQuyTrinh(id: String, body: Map<String, String>) = ApiClient.instance.updateQuyTrinhAdmin(id, body)
}
