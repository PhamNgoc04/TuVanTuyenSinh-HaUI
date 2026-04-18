package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.ApiClient
import com.codewithngoc.haui.tuyensinh.network.LoginRequest

class AuthRepository {
    fun login(request: LoginRequest) = ApiClient.instance.login(request)
    fun register(request: LoginRequest) = ApiClient.instance.register(request)
}
