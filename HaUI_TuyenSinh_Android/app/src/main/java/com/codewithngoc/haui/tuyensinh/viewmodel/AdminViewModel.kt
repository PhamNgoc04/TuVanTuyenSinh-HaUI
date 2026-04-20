package com.codewithngoc.haui.tuyensinh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithngoc.haui.tuyensinh.network.*
import com.codewithngoc.haui.tuyensinh.repository.AdminRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class AdminViewModel : ViewModel() {
    private val repository = AdminRepository()

    // ─── Status chung ────────────────────────────────────────────────────────
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _actionStatus = MutableLiveData<GenericResponse?>()
    val actionStatus: LiveData<GenericResponse?> = _actionStatus

    fun resetActionStatus() { _actionStatus.value = null }

    // ─── Helper để gọi action (add/update/delete) ─────────────────────────────
    private fun runAction(block: suspend () -> GenericResponse?) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                _actionStatus.postValue(block())
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", e.message))
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    // ─── Tai Khoan ───────────────────────────────────────────────────────────
    private val _users = MutableLiveData<List<TaiKhoanAdminItem>>()
    val users: LiveData<List<TaiKhoanAdminItem>> = _users

    fun fetchTaiKhoan() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = repository.getTaiKhoan().awaitResponse()
                if (res.isSuccessful && res.body()?.status == "SUCCESS")
                    _users.postValue(res.body()?.data ?: emptyList())
                else _actionStatus.postValue(GenericResponse("ERROR", "Lỗi HTTP ${res.code()}"))
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", "Lỗi kết nối: ${e.localizedMessage}"))
            } finally { _isLoading.postValue(false) }
        }
    }

    // ─── Nganh Hoc ───────────────────────────────────────────────────────────
    private val _nganhHocList = MutableLiveData<List<NganhHocItem>>()
    val nganhHocList: LiveData<List<NganhHocItem>> = _nganhHocList

    fun fetchNganhHoc() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = ApiClient.instance.getAllNganhHoc().awaitResponse()
                if (res.isSuccessful) _nganhHocList.postValue(res.body()?.data ?: emptyList())
                else _actionStatus.postValue(GenericResponse("ERROR", "Lỗi HTTP ${res.code()}"))
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", "Lỗi kết nối: ${e.localizedMessage}"))
            } finally { _isLoading.postValue(false) }
        }
    }

    fun addNganhHoc(maNganh: String, tenNganh: String, moTa: String) = runAction {
        repository.addNganhHoc(mapOf("maNganh" to maNganh, "tenNganh" to tenNganh, "moTa" to moTa)).awaitResponse().body()
    }
    fun updateNganhHoc(ma: String, tenNganh: String, moTa: String) = runAction {
        repository.updateNganhHoc(ma, mapOf("tenNganh" to tenNganh, "moTa" to moTa)).awaitResponse().body()
    }
    fun deleteNganhHoc(ma: String) = runAction { repository.deleteNganhHoc(ma).awaitResponse().body() }

    // ─── Tin Tuc ─────────────────────────────────────────────────────────────
    private val _tinTucList = MutableLiveData<List<TinTucItem>>()
    val tinTucList: LiveData<List<TinTucItem>> = _tinTucList

    fun fetchTinTuc() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = ApiClient.instance.getTinTuc().awaitResponse()
                if (res.isSuccessful) _tinTucList.postValue(res.body()?.data ?: emptyList())
                else _actionStatus.postValue(GenericResponse("ERROR", "Lỗi HTTP ${res.code()}"))
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", "Lỗi kết nối: ${e.localizedMessage}"))
            } finally { _isLoading.postValue(false) }
        }
    }

    fun addTinTuc(tieuDe: String, noiDung: String, moTa: String) = runAction {
        repository.addTinTuc(mapOf("tieuDe" to tieuDe, "noiDung" to noiDung, "moTa" to moTa)).awaitResponse().body()
    }
    fun updateTinTuc(id: String, tieuDe: String, noiDung: String, moTa: String) = runAction {
        repository.updateTinTuc(id, mapOf("tieuDe" to tieuDe, "noiDung" to noiDung, "moTa" to moTa)).awaitResponse().body()
    }
    fun deleteTinTuc(id: String) = runAction { repository.deleteTinTuc(id).awaitResponse().body() }

    // ─── Hoc Phi ─────────────────────────────────────────────────────────────
    private val _hocPhiList = MutableLiveData<List<HocPhiItem>>()
    val hocPhiList: LiveData<List<HocPhiItem>> = _hocPhiList

    fun fetchHocPhi() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = ApiClient.instance.getHocPhi().awaitResponse()
                if (res.isSuccessful) _hocPhiList.postValue(res.body()?.data ?: emptyList())
                else _actionStatus.postValue(GenericResponse("ERROR", "Lỗi HTTP ${res.code()}"))
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", "Lỗi kết nối: ${e.localizedMessage}"))
            } finally { _isLoading.postValue(false) }
        }
    }

    fun addHocPhi(soTien: String, namHoc: String) = runAction {
        repository.addHocPhi(mapOf("soTien" to soTien, "namHoc" to namHoc)).awaitResponse().body()
    }
    fun updateHocPhi(id: String, soTien: String, namHoc: String) = runAction {
        repository.updateHocPhi(id, mapOf("soTien" to soTien, "namHoc" to namHoc)).awaitResponse().body()
    }
    fun deleteHocPhi(id: String) = runAction { repository.deleteHocPhi(id).awaitResponse().body() }

    // ─── Quy Trinh ───────────────────────────────────────────────────────────
    private val _quyTrinhList = MutableLiveData<List<QuyTrinhItem>>()
    val quyTrinhList: LiveData<List<QuyTrinhItem>> = _quyTrinhList

    fun fetchQuyTrinh() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = ApiClient.instance.getQuyTrinh().awaitResponse()
                if (res.isSuccessful) _quyTrinhList.postValue(res.body()?.data ?: emptyList())
                else _actionStatus.postValue(GenericResponse("ERROR", "Lỗi HTTP ${res.code()}"))
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", "Lỗi kết nối: ${e.localizedMessage}"))
            } finally { _isLoading.postValue(false) }
        }
    }

    fun addQuyTrinh(noiDung: String) = runAction {
        repository.addQuyTrinh(mapOf("noiDung" to noiDung)).awaitResponse().body()
    }
    fun updateQuyTrinh(id: String, noiDung: String) = runAction {
        repository.updateQuyTrinh(id, mapOf("noiDung" to noiDung)).awaitResponse().body()
    }
    fun deleteQuyTrinh(id: String) = runAction { repository.deleteQuyTrinh(id).awaitResponse().body() }

    // ─── Hoc Bong ────────────────────────────────────────────────────────────
    private val _hocBongList = MutableLiveData<List<HocBongItem>>()
    val hocBongList: LiveData<List<HocBongItem>> = _hocBongList

    fun fetchHocBong() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = ApiClient.instance.getHocBong().awaitResponse()
                if (res.isSuccessful) _hocBongList.postValue(res.body()?.data ?: emptyList())
                else _actionStatus.postValue(GenericResponse("ERROR", "Lỗi HTTP ${res.code()}"))
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", "Lỗi kết nối: ${e.localizedMessage}"))
            } finally { _isLoading.postValue(false) }
        }
    }

    fun addHocBong(loaiHb: String, diemYc: String, hanhKiemYc: String) = runAction {
        repository.addHocBong(mapOf("loaiHb" to loaiHb, "diemYc" to diemYc, "hanhKiemYc" to hanhKiemYc)).awaitResponse().body()
    }
    fun updateHocBong(id: String, loaiHb: String, diemYc: String, hanhKiemYc: String) = runAction {
        repository.updateHocBong(id, mapOf("loaiHb" to loaiHb, "diemYc" to diemYc, "hanhKiemYc" to hanhKiemYc)).awaitResponse().body()
    }
    fun deleteHocBong(id: String) = runAction { repository.deleteHocBong(id).awaitResponse().body() }

    // ─── Chi Tieu ────────────────────────────────────────────────────────────
    private val _chiTieuList = MutableLiveData<List<ChiTieuItem>>()
    val chiTieuList: LiveData<List<ChiTieuItem>> = _chiTieuList

    fun fetchChiTieu(maNganh: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = ApiClient.instance.getChiTieu(maNganh).awaitResponse()
                if (res.isSuccessful) _chiTieuList.postValue(res.body()?.data ?: emptyList())
                else _actionStatus.postValue(GenericResponse("ERROR", "Lỗi HTTP ${res.code()}"))
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", "Lỗi kết nối: ${e.localizedMessage}"))
            } finally { _isLoading.postValue(false) }
        }
    }

    fun addChiTieu(maNganh: String, soLuong: String, nam: String, phuongThuc: String) = runAction {
        repository.addChiTieu(mapOf("maNganh" to maNganh, "soLuong" to soLuong, "nam" to nam, "phuongThuc" to phuongThuc)).awaitResponse().body()
    }
    fun updateChiTieu(id: String, soLuong: String, nam: String, phuongThuc: String) = runAction {
        repository.updateChiTieu(id, mapOf("soLuong" to soLuong, "nam" to nam, "phuongThuc" to phuongThuc)).awaitResponse().body()
    }
    fun deleteChiTieu(id: String) = runAction { repository.deleteChiTieu(id).awaitResponse().body() }

    // ─── Nghe Nghiep ─────────────────────────────────────────────────────────
    private val _ngheNghiepList = MutableLiveData<List<NgheNghiepItem>>()
    val ngheNghiepList: LiveData<List<NgheNghiepItem>> = _ngheNghiepList

    fun fetchNgheNghiep(maNganh: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = ApiClient.instance.getNgheNghiep(maNganh).awaitResponse()
                if (res.isSuccessful) _ngheNghiepList.postValue(res.body()?.data ?: emptyList())
                else _actionStatus.postValue(GenericResponse("ERROR", "Lỗi HTTP ${res.code()}"))
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", "Lỗi kết nối: ${e.localizedMessage}"))
            } finally { _isLoading.postValue(false) }
        }
    }

    fun addNgheNghiep(maNganh: String, tenNghe: String, mucLuong: String, tinhTrang: String) = runAction {
        repository.addNgheNghiep(mapOf("maNganh" to maNganh, "tenNghe" to tenNghe, "mucLuong" to mucLuong, "tinhTrang" to tinhTrang)).awaitResponse().body()
    }
    fun updateNgheNghiep(id: String, tenNghe: String, mucLuong: String, tinhTrang: String) = runAction {
        repository.updateNgheNghiep(id, mapOf("tenNghe" to tenNghe, "mucLuong" to mucLuong, "tinhTrang" to tinhTrang)).awaitResponse().body()
    }
    fun deleteNgheNghiep(id: String) = runAction { repository.deleteNgheNghiep(id).awaitResponse().body() }

    // ─── Phuong Thuc Xet Tuyen ───────────────────────────────────────────────
    private val _phuongThucList = MutableLiveData<List<PhuongThucItem>>()
    val phuongThucList: LiveData<List<PhuongThucItem>> = _phuongThucList

    fun fetchPhuongThuc() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val res = repository.getPhuongThuc().awaitResponse()
                if (res.isSuccessful) _phuongThucList.postValue(res.body()?.data ?: emptyList())
                else _actionStatus.postValue(GenericResponse("ERROR", "Lỗi HTTP ${res.code()}"))
            } catch (e: Exception) {
                _actionStatus.postValue(GenericResponse("ERROR", "Lỗi kết nối: ${e.localizedMessage}"))
            } finally { _isLoading.postValue(false) }
        }
    }

    fun addPhuongThuc(tenPhuongThuc: String, moTa: String) = runAction {
        repository.addPhuongThuc(mapOf("tenPhuongThuc" to tenPhuongThuc, "moTa" to moTa)).awaitResponse().body()
    }
    fun updatePhuongThuc(id: String, tenPhuongThuc: String, moTa: String) = runAction {
        repository.updatePhuongThuc(id, mapOf("tenPhuongThuc" to tenPhuongThuc, "moTa" to moTa)).awaitResponse().body()
    }
    fun deletePhuongThuc(id: String) = runAction { repository.deletePhuongThuc(id).awaitResponse().body() }

    // ─── Truong ──────────────────────────────────────────────────────────────
    fun updateTruong(ma: String, body: Map<String, String>) = runAction {
        repository.updateTruong(ma, body).awaitResponse().body()
    }
}
