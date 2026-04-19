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

    fun loadProfile(accountId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = repository.getNguoiDung(accountId).awaitResponse()
                if (res.isSuccessful) {
                    _userProfile.postValue(res.body())
                }
            } catch (e: Exception) {
                // Ignore error in demo
            }
        }
    }

    fun updateProfile(id: String, name: String, email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = repository.updateNguoiDung(id, mapOf("ten" to name, "email" to email)).awaitResponse()
                if (res.isSuccessful && res.body()?.get("status") == "SUCCESS") {
                    _updateStatus.postValue(true)
                } else {
                    _updateStatus.postValue(false)
                }
            } catch (e: Exception) {
                _updateStatus.postValue(false)
            }
        }
    }

    fun updateAvatar(id: String, avatarUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Backend will update only the fields sent in the map
                repository.updateNguoiDung(id, mapOf("avatar" to avatarUrl)).awaitResponse()
            } catch (e: Exception) {
                // Ignore error in demo
            }
        }
    }

    fun resetStatus() {
        _updateStatus.value = null
    }
}
