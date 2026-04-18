package com.codewithngoc.haui.tuyensinh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithngoc.haui.tuyensinh.network.AiChatRequest
import com.codewithngoc.haui.tuyensinh.network.AiChatResponse
import com.codewithngoc.haui.tuyensinh.repository.ChatAiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class ChatViewModel : ViewModel() {
    private val repository = ChatAiRepository()

    private val _aiResponse = MutableLiveData<AiChatResponse?>()
    val aiResponse: LiveData<AiChatResponse?> = _aiResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun askAi(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val response = repository.askAi(AiChatRequest(query)).awaitResponse()
                if (response.isSuccessful) {
                    _aiResponse.postValue(response.body())
                } else {
                    _error.postValue("Lỗi phản hồi từ AI")
                }
            } catch (e: Exception) {
                _error.postValue("Lỗi kết nối AI Service")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
