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
import com.codewithngoc.haui.tuyensinh.databinding.FragmentHomeBinding
import com.codewithngoc.haui.tuyensinh.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    // ✅ Fix #6: Dùng TinTucAdapter tách file + DiffUtil
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

        adapter = TinTucAdapter()
        binding.rvTinTuc.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTinTuc.adapter = adapter
        binding.rvTinTuc.isNestedScrollingEnabled = false

        binding.tvXemTatCa.setOnClickListener {
            // ✅ Fix: navigate đến màn hình danh sách tin tức riêng
            // thay vì chuyển sang tab Ngành Học (nav_news) — sai UX
            startActivity(android.content.Intent(requireContext(), TinTucListActivity::class.java))
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
            // ✅ Fix #5: submitList thay vì notifyDataSetChanged
            adapter.submitList(list)
        }

        viewModel.error.observe(viewLifecycleOwner) { msg ->
            if (!msg.isNullOrEmpty()) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
