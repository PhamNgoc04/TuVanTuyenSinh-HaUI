package com.codewithngoc.haui.tuyensinh.ui.main
import com.codewithngoc.haui.tuyensinh.*

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
import com.codewithngoc.haui.tuyensinh.databinding.FragmentNganhHocBinding
import com.codewithngoc.haui.tuyensinh.network.NganhHocItem
import com.codewithngoc.haui.tuyensinh.viewmodel.NganhViewModel

class NganhHocFragment : Fragment() {

    private var _binding: FragmentNganhHocBinding? = null
    private val binding get() = _binding!!
    // ✅ Fix #6: Dùng NganhAdapter tách file + DiffUtil
    private lateinit var adapter: NganhAdapter
    private val fullList = mutableListOf<NganhHocItem>()
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

        adapter = NganhAdapter()
        binding.rvNganhHoc.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNganhHoc.adapter = adapter

        setupObservers()
        viewModel.loadAllNganhHoc()

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val queryNoAccent = removeAccents(s.toString().lowercase().trim())
                val filtered = if (queryNoAccent.isEmpty()) fullList
                else fullList.filter {
                    removeAccents((it.tenNganh ?: "").lowercase()).contains(queryNoAccent)
                }
                // ✅ Fix #5: submitList thay vì notifyDataSetChanged
                adapter.submitList(filtered)
            }
        })
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoad ->
            binding.progressBar.visibility = if (isLoad) View.VISIBLE else View.GONE
        }

        viewModel.nganhList.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                fullList.clear()
                fullList.addAll(list)
                adapter.submitList(list.toList())
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
}
