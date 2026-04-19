package com.codewithngoc.haui.tuyensinh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithngoc.haui.tuyensinh.network.GenericResponse
import com.codewithngoc.haui.tuyensinh.network.TaiKhoanAdminItem
import com.codewithngoc.haui.tuyensinh.repository.AdminRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class AdminViewModel : ViewModel() {
    private val repository = AdminRepository()

    private val _users = MutableLiveData<List<TaiKhoanAdminItem>>()
    val users: LiveData<List<TaiKhoanAdminItem>> = _users

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _actionStatus = MutableLiveData<GenericResponse?>()
    val actionStatus: LiveData<GenericResponse?> = _actionStatus

    fun fetchTaiKhoan() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = repository.getTaiKhoan().awaitResponse()
                if (res.isSuccessful && res.body()?.status == "SUCCESS") {
                    _users.postValue(res.body()?.data ?: emptyList())
                }
            } catch (e: Exception) {
                // handle error
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun addTinTuc(tieuDe: String, noiDung: String, moTa: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val body = mapOf("tieuDe" to tieuDe, "noiDung" to noiDung, "moTa" to moTa)
                val res = repository.addTinTuc(body).awaitResponse()
                _actionStatus.postValue(res.body())
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", e.message))
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun addNganhHoc(maNganh: String, tenNganh: String, moTa: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val body = mapOf("maNganh" to maNganh, "tenNganh" to tenNganh, "moTa" to moTa)
                val res = repository.addNganhHoc(body).awaitResponse()
                _actionStatus.postValue(res.body())
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", e.message))
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
    fun addHocPhi(soTien: String, namHoc: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val body = mapOf("soTien" to soTien, "namHoc" to namHoc)
                val res = repository.addHocPhi(body).awaitResponse()
                _actionStatus.postValue(res.body())
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", e.message))
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun addQuyTrinh(noiDung: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val body = mapOf("noiDung" to noiDung)
                val res = repository.addQuyTrinh(body).awaitResponse()
                _actionStatus.postValue(res.body())
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", e.message))
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun addHocBong(tenHocBong: String, giaTriHocBong: String, dieuKien: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val body = mapOf("tenHocBong" to tenHocBong, "giaTriHocBong" to giaTriHocBong, "dieuKien" to dieuKien)
                val res = repository.addHocBong(body).awaitResponse()
                _actionStatus.postValue(res.body())
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", e.message))
            } finally { _isLoading.postValue(false) }
        }
    }

    fun addChiTieu(maNganh: String, soChiTieu: String, nam: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val body = mapOf("maNganh" to maNganh, "soChiTieu" to soChiTieu, "nam" to nam)
                val res = repository.addChiTieu(body).awaitResponse()
                _actionStatus.postValue(res.body())
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", e.message))
            } finally { _isLoading.postValue(false) }
        }
    }

    fun addNgheNghiep(maNganh: String, tenNgheNghiep: String, moTa: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val body = mapOf("maNganh" to maNganh, "tenNgheNghiep" to tenNgheNghiep, "moTa" to moTa)
                val res = repository.addNgheNghiep(body).awaitResponse()
                _actionStatus.postValue(res.body())
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", e.message))
            } finally { _isLoading.postValue(false) }
        }
    }

    fun addPhuongThuc(tenPhuongThuc: String, moTa: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val body = mapOf("tenPhuongThuc" to tenPhuongThuc, "moTa" to moTa)
                val res = repository.addPhuongThuc(body).awaitResponse()
                _actionStatus.postValue(res.body())
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", e.message))
            } finally { _isLoading.postValue(false) }
        }
    }

    fun updateTruong(maTruong: String, tenTruong: String, diaChi: String, gioiThieu: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val body = mapOf("tenTruong" to tenTruong, "diaChi" to diaChi, "gioiThieu" to gioiThieu)
                val res = repository.updateTruong(maTruong, body).awaitResponse()
                _actionStatus.postValue(res.body())
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", e.message))
            } finally { _isLoading.postValue(false) }
        }
    }

    fun resetActionStatus() {
        _actionStatus.value = null
    }
}
