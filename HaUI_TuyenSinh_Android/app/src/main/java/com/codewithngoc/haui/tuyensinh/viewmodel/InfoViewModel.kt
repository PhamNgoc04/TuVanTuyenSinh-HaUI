package com.codewithngoc.haui.tuyensinh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithngoc.haui.tuyensinh.network.*
import com.codewithngoc.haui.tuyensinh.repository.InfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class InfoViewModel : ViewModel() {
    private val repository = InfoRepository()

    private val _hocPhiList = MutableLiveData<List<HocPhiItem>>()
    val hocPhiList: LiveData<List<HocPhiItem>> = _hocPhiList

    private val _hocBongList = MutableLiveData<List<HocBongItem>>()
    val hocBongList: LiveData<List<HocBongItem>> = _hocBongList

    private val _quyTrinhList = MutableLiveData<List<QuyTrinhItem>>()
    val quyTrinhList: LiveData<List<QuyTrinhItem>> = _quyTrinhList

    private val _chuongTrinhList = MutableLiveData<List<ChuongTrinhHocItem>>()
    val chuongTrinhList: LiveData<List<ChuongTrinhHocItem>> = _chuongTrinhList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadAllInfo()
    }

    private fun loadAllInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val (hpRes, hbRes, qtRes, ctRes) = listOf(
                    repository.getHocPhi().awaitResponse(),
                    repository.getHocBong().awaitResponse(),
                    repository.getQuyTrinh().awaitResponse(),
                    repository.getChuongTrinhHoc().awaitResponse()
                )

                if (hpRes.isSuccessful) _hocPhiList.postValue((hpRes.body() as? HocPhiListResponse)?.data ?: emptyList())
                if (hbRes.isSuccessful) _hocBongList.postValue((hbRes.body() as? HocBongListResponse)?.data ?: emptyList())
                if (qtRes.isSuccessful) _quyTrinhList.postValue((qtRes.body() as? QuyTrinhListResponse)?.data ?: emptyList())
                if (ctRes.isSuccessful) _chuongTrinhList.postValue((ctRes.body() as? ChuongTrinhHocListResponse)?.data ?: emptyList())
            } catch (e: Exception) {
                // handle error
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
