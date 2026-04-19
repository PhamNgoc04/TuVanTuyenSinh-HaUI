package com.codewithngoc.haui.tuyensinh.ui.admin

import com.codewithngoc.haui.tuyensinh.network.NganhHocItem

class AdminNganhHocManageActivity : BaseAdminManageActivity<NganhHocItem>() {

    override fun screenTitle() = "Quản lý Ngành Học"
    override fun getItemIcon() = "🎓"
    override fun loadList() = viewModel.fetchNganhHoc()

    override fun observeList() {
        viewModel.nganhHocList.observe(this) { list ->
            adapter.submitList(list)
            binding.layoutEmpty.visibility =
                if (list.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }
    }

    override fun mapItemToDisplay(item: NganhHocItem) =
        Pair(item.tenNganh ?: "—", "Mã: ${item.maNganh ?: "?"} • ${item.moTa?.take(40) ?: ""}")

    override fun getItemId(item: NganhHocItem) = item.maNganh ?: ""

    override fun showAddDialog() {
        openBottomSheet(
            title = "➕ Thêm Ngành Học",
            fields = listOf(
                Triple(0, "Mã ngành (vd: CNTT)", ""),
                Triple(1, "Tên ngành", ""),
                Triple(2, "Mô tả", "")
            )
        ) { values ->
            viewModel.addNganhHoc(values[0]!!, values[1]!!, values[2]!!)
        }
    }

    override fun showEditDialog(item: NganhHocItem) {
        openBottomSheet(
            title = "✏️ Sửa Ngành Học",
            fields = listOf(
                Triple(0, "Tên ngành", item.tenNganh ?: ""),
                Triple(1, "Mô tả", item.moTa ?: "")
            )
        ) { values ->
            viewModel.updateNganhHoc(item.maNganh ?: "", values[0]!!, values[1]!!)
        }
    }

    override fun performDelete(item: NganhHocItem) = viewModel.deleteNganhHoc(item.maNganh ?: "")
}
