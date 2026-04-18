package com.codewithngoc.haui.tuyensinh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithngoc.haui.tuyensinh.network.TinTucItem
import com.codewithngoc.haui.tuyensinh.network.TruongItem
import com.codewithngoc.haui.tuyensinh.repository.TruongRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class HomeViewModel : ViewModel() {
    private val repository = TruongRepository()

    private val _truongInfo = MutableLiveData<TruongItem?>()
    val truongInfo: LiveData<TruongItem?> = _truongInfo

    private val _tinTucList = MutableLiveData<List<TinTucItem>>()
    val tinTucList: LiveData<List<TinTucItem>> = _tinTucList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val truongRes = repository.getThongTinTruong().awaitResponse()
                if (truongRes.isSuccessful && truongRes.body()?.status == "SUCCESS") {
                    _truongInfo.postValue(truongRes.body()?.data?.firstOrNull())
                }

                val tinTucRes = repository.getTinTuc().awaitResponse()
                if (tinTucRes.isSuccessful && tinTucRes.body()?.status == "SUCCESS") {
                    _tinTucList.postValue(tinTucRes.body()?.data ?: emptyList())
                }
            } catch (e: Exception) {
                _error.postValue("Lỗi tải dữ liệu Homepage")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
