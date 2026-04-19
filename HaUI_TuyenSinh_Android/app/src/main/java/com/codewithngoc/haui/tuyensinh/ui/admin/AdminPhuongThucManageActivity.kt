package com.codewithngoc.haui.tuyensinh.ui.admin

import com.codewithngoc.haui.tuyensinh.network.PhuongThucItem

class AdminPhuongThucManageActivity : BaseAdminManageActivity<PhuongThucItem>() {

    override fun screenTitle() = "Quản lý Phương Thức Xét Tuyển"
    override fun getItemIcon() = "📝"
    override fun loadList() = viewModel.fetchPhuongThuc()

    override fun observeList() {
        viewModel.phuongThucList.observe(this) { list ->
            adapter.submitList(list)
            binding.layoutEmpty.visibility =
                if (list.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }
    }

    override fun mapItemToDisplay(item: PhuongThucItem) =
        Pair(item.tenPhuongThuc ?: "—", item.moTa?.take(60) ?: "Chưa có mô tả")

    override fun getItemId(item: PhuongThucItem) = item.id ?: ""

    override fun showAddDialog() {
        openBottomSheet(
            title = "➕ Thêm Phương Thức Xét Tuyển",
            fields = listOf(
                Triple(0, "Tên phương thức (vd: Xét học bạ)", ""),
                Triple(1, "Mô tả chi tiết", "")
            )
        ) { values ->
            viewModel.addPhuongThuc(values[0]!!, values[1]!!)
        }
    }

    override fun showEditDialog(item: PhuongThucItem) {
        openBottomSheet(
            title = "✏️ Sửa Phương Thức Xét Tuyển",
            fields = listOf(
                Triple(0, "Tên phương thức", item.tenPhuongThuc ?: ""),
                Triple(1, "Mô tả chi tiết", item.moTa ?: "")
            )
        ) { values ->
            viewModel.updatePhuongThuc(item.id ?: "", values[0]!!, values[1]!!)
        }
    }

    override fun performDelete(item: PhuongThucItem) = viewModel.deletePhuongThuc(item.id ?: "")
}
