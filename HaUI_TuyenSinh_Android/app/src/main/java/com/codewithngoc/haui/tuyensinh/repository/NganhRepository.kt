package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.ApiClient

class NganhRepository {
    fun getAllNganhHoc() = ApiClient.instance.getAllNganhHoc()
    fun getChiTietNganhHoc(maNganh: String) = ApiClient.instance.getChiTietNganhHoc(maNganh)
    fun getNgheNghiep(maNganh: String) = ApiClient.instance.getNgheNghiep(maNganh)
    fun getChiTieu(maNganh: String) = ApiClient.instance.getChiTieu(maNganh)
    fun getYeuCauDauVao(maNganh: String) = ApiClient.instance.getYeuCauDauVao(maNganh)
    fun getTinChi(maNganh: String) = ApiClient.instance.getTinChi(maNganh)
}
