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
                    val body = response.body()
                    if (body != null && !body.reply.isNullOrEmpty()) {
                        _aiResponse.postValue(body)
                    } else {
                        _error.postValue("⚠️ AI Service không trả về nội dung. Code: ${response.code()}")
                    }
                } else {
                    // ✅ Phân biệt các loại lỗi HTTP
                    val errMsg = when (response.code()) {
                        404 -> "❌ Endpoint AI không tồn tại (404). Kiểm tra lại URL AI Service."
                        500 -> "❌ AI Service bị lỗi nội bộ (500). Kiểm tra lại GEMINI_API_KEY."
                        else -> "❌ Lỗi phản hồi từ AI (HTTP ${response.code()})"
                    }
                    _error.postValue(errMsg)
                }
            } catch (e: java.net.SocketTimeoutException) {
                _error.postValue("⏰ AI Service phản hồi quá chậm. Bạn thử lại sau nhé!")
            } catch (e: java.net.ConnectException) {
                _error.postValue("📡 Không kết nối được AI Service. Kiểm tra: server đang chạy?  IP đúng?")
            } catch (e: Exception) {
                _error.postValue("⚠️ Lỗi: ${e.localizedMessage ?: e.javaClass.simpleName}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
