package com.codewithngoc.haui.tuyensinh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithngoc.haui.tuyensinh.network.AuthResponse
import com.codewithngoc.haui.tuyensinh.network.LoginRequest
import com.codewithngoc.haui.tuyensinh.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()

    private val _loginResult = MutableLiveData<AuthResponse?>()
    val loginResult: LiveData<AuthResponse?> = _loginResult

    private val _registerResult = MutableLiveData<AuthResponse?>()
    val registerResult: LiveData<AuthResponse?> = _registerResult

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun login(req: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val response = repository.login(req).awaitResponse()
                if (response.isSuccessful) {
                    _loginResult.postValue(response.body())
                } else {
                    _error.postValue("Sai thông tin đăng nhập!")
                }
            } catch (e: Exception) {
                _error.postValue("Lỗi kết nối mạng: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun register(req: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val response = repository.register(req).awaitResponse()
                if (response.isSuccessful) {
                    _registerResult.postValue(response.body())
                } else {
                    _error.postValue("Đăng ký thất bại!")
                }
            } catch (e: Exception) {
                _error.postValue("Lỗi kết nối mạng: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
