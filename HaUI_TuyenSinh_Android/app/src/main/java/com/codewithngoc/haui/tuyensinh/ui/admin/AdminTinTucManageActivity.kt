package com.codewithngoc.haui.tuyensinh.ui.admin

import com.codewithngoc.haui.tuyensinh.network.TinTucItem

class AdminTinTucManageActivity : BaseAdminManageActivity<TinTucItem>() {

    override fun screenTitle() = "Quản lý Tin Tức"
    override fun getItemIcon() = "📰"
    override fun loadList() = viewModel.fetchTinTuc()

    override fun observeList() {
        viewModel.tinTucList.observe(this) { list ->
            adapter.submitList(list)
            binding.layoutEmpty.visibility =
                if (list.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }
    }

    override fun mapItemToDisplay(item: TinTucItem) =
        Pair(item.tieuDe ?: "—", item.moTa?.take(60) ?: "Chưa có mô tả")

    override fun getItemId(item: TinTucItem) = item.id ?: ""

    override fun showAddDialog() {
        openBottomSheet(
            title = "➕ Thêm Tin Tức",
            fields = listOf(
                Triple(0, "Tiêu đề", ""),
                Triple(1, "Mô tả ngắn", ""),
                Triple(2, "Nội dung", "")
            )
        ) { values ->
            viewModel.addTinTuc(values[0]!!, values[2]!!, values[1]!!)
        }
    }

    override fun showEditDialog(item: TinTucItem) {
        openBottomSheet(
            title = "✏️ Sửa Tin Tức",
            fields = listOf(
                Triple(0, "Tiêu đề", item.tieuDe ?: ""),
                Triple(1, "Mô tả ngắn", item.moTa ?: ""),
                Triple(2, "Nội dung", item.noiDung ?: "")
            )
        ) { values ->
            viewModel.updateTinTuc(item.id ?: "", values[0]!!, values[2]!!, values[1]!!)
        }
    }

    override fun performDelete(item: TinTucItem) = viewModel.deleteTinTuc(item.id ?: "")
}
