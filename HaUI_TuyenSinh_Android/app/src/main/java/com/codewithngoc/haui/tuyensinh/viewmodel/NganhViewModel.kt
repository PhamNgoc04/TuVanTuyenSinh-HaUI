package com.codewithngoc.haui.tuyensinh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithngoc.haui.tuyensinh.network.*
import com.codewithngoc.haui.tuyensinh.repository.NganhRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class NganhViewModel : ViewModel() {
    private val repository = NganhRepository()

    private val _nganhList = MutableLiveData<List<NganhHocItem>>()
    val nganhList: LiveData<List<NganhHocItem>> = _nganhList

    private val _nganhDetail = MutableLiveData<NganhHocItem?>()
    val nganhDetail: LiveData<NganhHocItem?> = _nganhDetail

    private val _ngheNghiepList = MutableLiveData<List<NgheNghiepItem>>()
    val ngheNghiepList: LiveData<List<NgheNghiepItem>> = _ngheNghiepList

    private val _chiTieuList = MutableLiveData<List<ChiTieuItem>>()
    val chiTieuList: LiveData<List<ChiTieuItem>> = _chiTieuList

    private val _yeuCauList = MutableLiveData<List<YeuCauDauVaoItem>>()
    val yeuCauList: LiveData<List<YeuCauDauVaoItem>> = _yeuCauList

    private val _tinChiList = MutableLiveData<List<TinChiItem>>()
    val tinChiList: LiveData<List<TinChiItem>> = _tinChiList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadAllNganhHoc() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = repository.getAllNganhHoc().awaitResponse()
                if (res.isSuccessful) {
                    _nganhList.postValue(res.body()?.data ?: emptyList())
                }
            } catch (e: Exception) {
                // error
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun loadNganhDetailData(maNganh: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val detailRes = repository.getChiTietNganhHoc(maNganh).awaitResponse()
                if (detailRes.isSuccessful) _nganhDetail.postValue(detailRes.body())

                val nnRes = repository.getNgheNghiep(maNganh).awaitResponse()
                if (nnRes.isSuccessful) _ngheNghiepList.postValue(nnRes.body()?.data ?: emptyList())

                val ctRes = repository.getChiTieu(maNganh).awaitResponse()
                if (ctRes.isSuccessful) _chiTieuList.postValue(ctRes.body()?.data ?: emptyList())

                val ycRes = repository.getYeuCauDauVao(maNganh).awaitResponse()
                if (ycRes.isSuccessful) _yeuCauList.postValue(ycRes.body()?.data ?: emptyList())

                val tcRes = repository.getTinChi(maNganh).awaitResponse()
                if (tcRes.isSuccessful) _tinChiList.postValue(tcRes.body()?.data ?: emptyList())
            } catch (e: Exception) {
                // error
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
