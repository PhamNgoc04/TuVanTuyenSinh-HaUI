package com.codewithngoc.haui.tuyensinh.ui.admin

import com.codewithngoc.haui.tuyensinh.network.QuyTrinhItem

class AdminQuyTrinhManageActivity : BaseAdminManageActivity<QuyTrinhItem>() {

    override fun screenTitle() = "Quản lý Quy Trình Nhập Học"
    override fun getItemIcon() = "📋"
    override fun loadList() = viewModel.fetchQuyTrinh()

    override fun observeList() {
        viewModel.quyTrinhList.observe(this) { list ->
            adapter.submitList(list)
            binding.layoutEmpty.visibility =
                if (list.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }
    }

    override fun mapItemToDisplay(item: QuyTrinhItem) =
        Pair("Bước #${item.id ?: "?"}", item.noiDung?.take(80) ?: "Chưa có nội dung")

    override fun getItemId(item: QuyTrinhItem) = item.id ?: ""

    override fun showAddDialog() {
        openBottomSheet(
            title = "➕ Thêm Bước Quy Trình",
            fields = listOf(
                Triple(0, "Nội dung bước thực hiện", "")
            )
        ) { values ->
            viewModel.addQuyTrinh(values[0]!!)
        }
    }

    override fun showEditDialog(item: QuyTrinhItem) {
        openBottomSheet(
            title = "✏️ Sửa Bước Quy Trình",
            fields = listOf(
                Triple(0, "Nội dung bước thực hiện", item.noiDung ?: "")
            )
        ) { values ->
            viewModel.updateQuyTrinh(item.id ?: "", values[0]!!)
        }
    }

    override fun performDelete(item: QuyTrinhItem) = viewModel.deleteQuyTrinh(item.id ?: "")
}
