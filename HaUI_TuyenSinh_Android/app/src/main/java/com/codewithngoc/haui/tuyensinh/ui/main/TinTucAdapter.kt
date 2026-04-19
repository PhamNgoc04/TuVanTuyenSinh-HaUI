package com.codewithngoc.haui.tuyensinh.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codewithngoc.haui.tuyensinh.databinding.ItemTinTucBinding
import com.codewithngoc.haui.tuyensinh.network.TinTucItem

/**
 * Fix #5 & #6: Tách ra file riêng + dùng ListAdapter với DiffUtil
 * thay vì notifyDataSetChanged() (hiệu năng kém).
 */
class TinTucAdapter : ListAdapter<TinTucItem, TinTucAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TinTucItem>() {
            override fun areItemsTheSame(old: TinTucItem, new: TinTucItem) =
                old.id == new.id
            override fun areContentsTheSame(old: TinTucItem, new: TinTucItem) =
                old == new
        }
    }

    inner class ViewHolder(val binding: ItemTinTucBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemTinTucBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.tvTieuDe.text = item.tieuDe
        holder.binding.tvMoTa.text = item.moTa
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, TinTucDetailActivity::class.java).apply {
                putExtra("TIEU_DE", item.tieuDe)
                putExtra("MO_TA", item.moTa)
                putExtra("NOI_DUNG", item.noiDung)
            }
            holder.itemView.context.startActivity(intent)
        }
    }
}
