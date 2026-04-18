package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.ApiClient

class InfoRepository {
    fun getHocPhi() = ApiClient.instance.getHocPhi()
    fun getHocBong() = ApiClient.instance.getHocBong()
    fun getQuyTrinh() = ApiClient.instance.getQuyTrinh()
    fun getChuongTrinhHoc() = ApiClient.instance.getChuongTrinhHoc()
}
