package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.ApiClient

class UserRepository {
    fun getNguoiDung(id: String) = ApiClient.instance.getNguoiDung(id)
    fun updateNguoiDung(id: String, body: Map<String, String>) = ApiClient.instance.updateNguoiDung(id, body)
}
