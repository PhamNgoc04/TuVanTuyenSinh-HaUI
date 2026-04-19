package com.codewithngoc.haui.tuyensinh.ui.admin

import com.codewithngoc.haui.tuyensinh.network.HocBongItem

class AdminHocBongManageActivity : BaseAdminManageActivity<HocBongItem>() {

    override fun screenTitle() = "Quản lý Học Bổng"
    override fun getItemIcon() = "🏆"
    override fun loadList() = viewModel.fetchHocBong()

    override fun observeList() {
        viewModel.hocBongList.observe(this) { list ->
            adapter.submitList(list)
            binding.layoutEmpty.visibility =
                if (list.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }
    }

    override fun mapItemToDisplay(item: HocBongItem) =
        Pair(
            item.loaiHb ?: "Học bổng",
            "Điểm YC: ${item.diemYc ?: "?"} • Hạnh kiểm: ${item.hanhKiemYc ?: "?"}"
        )

    override fun getItemId(item: HocBongItem) = item.id ?: ""

    override fun showAddDialog() {
        openBottomSheet(
            title = "➕ Thêm Học Bổng",
            fields = listOf(
                Triple(0, "Loại học bổng", ""),
                Triple(1, "Điểm yêu cầu (vd: 8.0)", ""),
                Triple(2, "Hạnh kiểm yêu cầu (Tốt/Khá)", "")
            )
        ) { values ->
            viewModel.addHocBong(values[0]!!, values[1]!!, values[2]!!)
        }
    }

    override fun showEditDialog(item: HocBongItem) {
        openBottomSheet(
            title = "✏️ Sửa Học Bổng",
            fields = listOf(
                Triple(0, "Loại học bổng", item.loaiHb ?: ""),
                Triple(1, "Điểm yêu cầu", item.diemYc ?: ""),
                Triple(2, "Hạnh kiểm yêu cầu", item.hanhKiemYc ?: "")
            )
        ) { values ->
            viewModel.updateHocBong(item.id ?: "", values[0]!!, values[1]!!, values[2]!!)
        }
    }

    override fun performDelete(item: HocBongItem) = viewModel.deleteHocBong(item.id ?: "")
}
