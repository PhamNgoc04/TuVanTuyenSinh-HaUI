package com.codewithngoc.haui.tuyensinh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithngoc.haui.tuyensinh.network.NguoiDungResponse
import com.codewithngoc.haui.tuyensinh.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class ProfileViewModel : ViewModel() {
    private val repository = UserRepository()

    private val _userProfile = MutableLiveData<NguoiDungResponse?>()
    val userProfile: LiveData<NguoiDungResponse?> = _userProfile

    private val _updateStatus = MutableLiveData<Boolean?>()
    val updateStatus: LiveData<Boolean?> = _updateStatus

    // ✅ Fix #3: Thêm LiveData error để UI hiển thị lỗi
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadProfile(accountId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = repository.getNguoiDung(accountId).awaitResponse()
                if (res.isSuccessful && res.body() != null) {
                    _userProfile.postValue(res.body())
                } else {
                    // ✅ Không im lặng nữa — báo lỗi rõ ràng
                    _error.postValue("Không thể tải hồ sơ (HTTP ${res.code()})")
                    _userProfile.postValue(null)
                }
            } catch (e: Exception) {
                // ✅ Bắt lỗi mạng và thông báo cho UI
                _error.postValue("Lỗi kết nối: ${e.localizedMessage ?: "Vui lòng kiểm tra mạng"}")
                _userProfile.postValue(null)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun updateProfile(id: String, name: String, email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = repository.updateNguoiDung(id, mapOf("ten" to name, "email" to email)).awaitResponse()
                if (res.isSuccessful && res.body()?.get("status") == "SUCCESS") {
                    _updateStatus.postValue(true)
                    // Reload profile sau khi update thành công
                    loadProfile(id)
                } else {
                    _updateStatus.postValue(false)
                    _error.postValue("Cập nhật thất bại (HTTP ${res.code()})")
                }
            } catch (e: Exception) {
                _updateStatus.postValue(false)
                _error.postValue("Lỗi kết nối: ${e.localizedMessage}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun updateAvatar(id: String, avatarUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updateNguoiDung(id, mapOf("avatar" to avatarUrl)).awaitResponse()
            } catch (e: Exception) {
                _error.postValue("Không thể cập nhật ảnh đại diện")
            }
        }
    }

    fun resetStatus() {
        _updateStatus.value = null
    }

    fun clearError() {
        _error.value = null
    }
}
