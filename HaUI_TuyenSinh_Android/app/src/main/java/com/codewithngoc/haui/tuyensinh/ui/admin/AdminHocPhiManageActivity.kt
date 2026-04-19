package com.codewithngoc.haui.tuyensinh.ui.admin

import com.codewithngoc.haui.tuyensinh.network.HocPhiItem

class AdminHocPhiManageActivity : BaseAdminManageActivity<HocPhiItem>() {

    override fun screenTitle() = "Quản lý Học Phí"
    override fun getItemIcon() = "💰"
    override fun loadList() = viewModel.fetchHocPhi()

    override fun observeList() {
        viewModel.hocPhiList.observe(this) { list ->
            adapter.submitList(list)
            binding.layoutEmpty.visibility =
                if (list.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }
    }

    override fun mapItemToDisplay(item: HocPhiItem) =
        Pair(
            "Năm học ${item.namHoc ?: "?"}",
            "${formatMoney(item.soTien)} VNĐ/tín chỉ"
        )

    private fun formatMoney(s: String?): String {
        val num = s?.toLongOrNull() ?: return s ?: "—"
        return String.format("%,d", num).replace(",", ".")
    }

    override fun getItemId(item: HocPhiItem) = item.id ?: ""

    override fun showAddDialog() {
        openBottomSheet(
            title = "➕ Thêm Học Phí",
            fields = listOf(
                Triple(0, "Số tiền (VNĐ/tín chỉ)", ""),
                Triple(1, "Năm học (vd: 2024-2025)", "")
            )
        ) { values ->
            viewModel.addHocPhi(values[0]!!, values[1]!!)
        }
    }

    override fun showEditDialog(item: HocPhiItem) {
        openBottomSheet(
            title = "✏️ Sửa Học Phí",
            fields = listOf(
                Triple(0, "Số tiền (VNĐ/tín chỉ)", item.soTien ?: ""),
                Triple(1, "Năm học", item.namHoc ?: "")
            )
        ) { values ->
            viewModel.updateHocPhi(item.id ?: "", values[0]!!, values[1]!!)
        }
    }

    override fun performDelete(item: HocPhiItem) = viewModel.deleteHocPhi(item.id ?: "")
}
