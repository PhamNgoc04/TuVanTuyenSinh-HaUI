package com.codewithngoc.haui.tuyensinh.ui.main
import com.codewithngoc.haui.tuyensinh.*

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codewithngoc.haui.tuyensinh.databinding.FragmentNganhHocBinding
import com.codewithngoc.haui.tuyensinh.databinding.ItemNganhHocBinding
import com.codewithngoc.haui.tuyensinh.network.NganhHocItem
import com.codewithngoc.haui.tuyensinh.ui.course.NganhHocDetailActivity
import com.codewithngoc.haui.tuyensinh.viewmodel.NganhViewModel

class NganhHocFragment : Fragment() {

    private var _binding: FragmentNganhHocBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NganhAdapter
    private val nganhList = mutableListOf<NganhHocItem>()
    private val filteredList = mutableListOf<NganhHocItem>()
    private lateinit var viewModel: NganhViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNganhHocBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[NganhViewModel::class.java]

        adapter = NganhAdapter(filteredList)
        binding.rvNganhHoc.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNganhHoc.adapter = adapter

        setupObservers()
        viewModel.loadAllNganhHoc()

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase().trim()
                val queryNoAccent = removeAccents(query)
                filteredList.clear()
                if (queryNoAccent.isEmpty()) {
                    filteredList.addAll(nganhList)
                } else {
                    filteredList.addAll(nganhList.filter { 
                        val nameNoAccent = removeAccents((it.tenNganh ?: "").lowercase())
                        nameNoAccent.contains(queryNoAccent) 
                    })
                }
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoad ->
            binding.progressBar.visibility = if(isLoad) View.VISIBLE else View.GONE
        }

        viewModel.nganhList.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                nganhList.clear()
                nganhList.addAll(list)
                filteredList.clear()
                filteredList.addAll(list)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "Lỗi tải ngành học", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun removeAccents(str: String): String {
        val normalized = java.text.Normalizer.normalize(str, java.text.Normalizer.Form.NFD)
        return normalized.replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
            .replace('đ', 'd').replace('Đ', 'D')
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class NganhAdapter(private val list: List<NganhHocItem>) : RecyclerView.Adapter<NganhAdapter.ViewHolder>() {
        inner class ViewHolder(val binding: ItemNganhHocBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ItemNganhHocBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = list[position]
            holder.binding.tvTenNganh.text = item.tenNganh
            holder.binding.tvMaNganh.text = "Mã ngành: ${item.maNganh}"
            holder.binding.tvMoTa.text = item.moTa
            
            holder.itemView.setOnClickListener {
                val intent = Intent(requireContext(), NganhHocDetailActivity::class.java)
                intent.putExtra("MA_NGANH", item.maNganh)
                startActivity(intent)
            }
        }

        override fun getItemCount() = list.size
    }
}
