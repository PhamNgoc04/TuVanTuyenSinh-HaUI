package com.codewithngoc.haui.tuyensinh.ui.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codewithngoc.haui.tuyensinh.databinding.ItemAdminManageBinding

/**
 * Generic RecyclerView Adapter dùng chung cho mọi Admin Management Screen.
 * T = kiểu dữ liệu của entity (NganhHocItem, TinTucItem, ...)
 *
 * @param iconEmoji   Emoji hiển thị ở icon circle
 * @param mapDisplay  Hàm chuyển đổi item → (title, subtitle) để hiển thị
 * @param onEdit      Callback khi bấm nút Sửa
 * @param onDelete    Callback khi bấm nút Xóa
 */
class AdminManageAdapter<T : Any>(
    private val iconEmoji: String,
    private val mapDisplay: (T) -> Pair<String, String>,
    private val onEdit: (T) -> Unit,
    private val onDelete: (T) -> Unit
) : ListAdapter<T, AdminManageAdapter<T>.ViewHolder>(object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem === newItem
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}) {

    inner class ViewHolder(val binding: ItemAdminManageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAdminManageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val (title, subtitle) = mapDisplay(item)

        with(holder.binding) {
            tvIcon.text = iconEmoji
            tvTitle.text = title
            tvSubtitle.text = subtitle
            btnEdit.setOnClickListener { onEdit(item) }
            btnDelete.setOnClickListener { onDelete(item) }
        }
    }
}
