package com.codewithngoc.haui.tuyensinh.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codewithngoc.haui.tuyensinh.databinding.ItemNganhHocBinding
import com.codewithngoc.haui.tuyensinh.network.NganhHocItem
import com.codewithngoc.haui.tuyensinh.ui.course.NganhHocDetailActivity

/**
 * Fix #5 & #6: Tách ra file riêng + dùng ListAdapter với DiffUtil
 */
class NganhAdapter : ListAdapter<NganhHocItem, NganhAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NganhHocItem>() {
            override fun areItemsTheSame(old: NganhHocItem, new: NganhHocItem) =
                old.maNganh == new.maNganh
            override fun areContentsTheSame(old: NganhHocItem, new: NganhHocItem) =
                old == new
        }
    }

    inner class ViewHolder(val binding: ItemNganhHocBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemNganhHocBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.tvTenNganh.text = item.tenNganh
        holder.binding.tvMaNganh.text = "Mã ngành: ${item.maNganh}"
        holder.binding.tvMoTa.text = item.moTa
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, NganhHocDetailActivity::class.java).apply {
                putExtra("MA_NGANH", item.maNganh)
            }
            holder.itemView.context.startActivity(intent)
        }
    }
}
