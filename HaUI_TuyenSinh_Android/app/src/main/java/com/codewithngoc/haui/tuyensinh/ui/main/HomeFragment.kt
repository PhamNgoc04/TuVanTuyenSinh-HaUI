package com.codewithngoc.haui.tuyensinh.ui.main
import com.codewithngoc.haui.tuyensinh.*

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codewithngoc.haui.tuyensinh.databinding.FragmentHomeBinding
import com.codewithngoc.haui.tuyensinh.databinding.ItemTinTucBinding
import com.codewithngoc.haui.tuyensinh.network.TinTucItem
import com.codewithngoc.haui.tuyensinh.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: TinTucAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        adapter = TinTucAdapter(emptyList())
        binding.rvTinTuc.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTinTuc.adapter = adapter
        binding.rvTinTuc.isNestedScrollingEnabled = false
        binding.tvXemTatCa.setOnClickListener {
            requireActivity().findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.nav_news
        }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.truongInfo.observe(viewLifecycleOwner) { truong ->
            if (truong != null) {
                binding.tvAddress.text = "${truong.duong}, ${truong.quan}, ${truong.thanhPho}"
            }
        }

        viewModel.tinTucList.observe(viewLifecycleOwner) { list ->
            adapter.updateData(list)
        }

        viewModel.error.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class TinTucAdapter(private var items: List<TinTucItem>) :
        RecyclerView.Adapter<TinTucAdapter.ViewHolder>() {

        fun updateData(newItems: List<TinTucItem>) {
            items = newItems
            notifyDataSetChanged()
        }

        inner class ViewHolder(val binding: ItemTinTucBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(ItemTinTucBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.binding.tvTieuDe.text = item.tieuDe
            holder.binding.tvMoTa.text = item.moTa

            holder.itemView.setOnClickListener {
                val intent = android.content.Intent(holder.itemView.context, TinTucDetailActivity::class.java).apply {
                    putExtra("TIEU_DE", item.tieuDe)
                    putExtra("MO_TA", item.moTa)
                    putExtra("NOI_DUNG", item.noiDung)
                }
                holder.itemView.context.startActivity(intent)
            }
        }

        override fun getItemCount() = items.size
    }
}

