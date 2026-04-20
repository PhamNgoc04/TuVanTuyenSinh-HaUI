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
import com.codewithngoc.haui.tuyensinh.HaUIApplication
import com.codewithngoc.haui.tuyensinh.data.LocalCache

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
        val context = HaUIApplication.appContext // Cần context để gọi Caches

        // Hiển thị ngay lập tức Data cũ từ Cache
        LocalCache.getThongTinTruong(context)?.let { _truongInfo.postValue(it) }
        LocalCache.getTinTuc(context)?.let { _tinTucList.postValue(it) }

        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val truongRes = repository.getThongTinTruong().awaitResponse()
                if (truongRes.isSuccessful && truongRes.body()?.status == "SUCCESS") {
                    val truongData = truongRes.body()?.data?.firstOrNull()
                    _truongInfo.postValue(truongData)
                    truongData?.let { LocalCache.saveThongTinTruong(context, it) } // Cache lại bản mới
                }

                val tinTucRes = repository.getTinTuc().awaitResponse()
                if (tinTucRes.isSuccessful && tinTucRes.body()?.status == "SUCCESS") {
                    val tinTucData = tinTucRes.body()?.data ?: emptyList()
                    _tinTucList.postValue(tinTucData)
                    LocalCache.saveTinTuc(context, tinTucData) // Cache lại bản mới
                }
            } catch (e: Exception) {
                // Chỉ quăng Toast nếu Cache TRỐNG HOÀN TOÀN (Lần mở app đầu tiên bị lỗi)
                if (_truongInfo.value == null && _tinTucList.value.isNullOrEmpty()) {
                    _error.postValue("Không có kết nối, không thể tải dữ liệu!")
                } else {
                    _error.postValue("Đang xem chế độ ngoại tuyến (Không có mạng/Server lỗi)")
                }
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
