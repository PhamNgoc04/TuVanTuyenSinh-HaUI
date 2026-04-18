package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.ApiClient

class TruongRepository {
    fun getThongTinTruong() = ApiClient.instance.getThongTinTruong()
    fun getTinTuc() = ApiClient.instance.getTinTuc()
    fun getNganhHoc() = ApiClient.instance.getAllNganhHoc()
}
