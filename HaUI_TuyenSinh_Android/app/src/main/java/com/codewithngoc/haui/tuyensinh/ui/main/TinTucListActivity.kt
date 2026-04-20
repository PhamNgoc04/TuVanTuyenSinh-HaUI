package com.codewithngoc.haui.tuyensinh.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codewithngoc.haui.tuyensinh.R
import com.codewithngoc.haui.tuyensinh.databinding.ActivityTinTucListBinding
import com.codewithngoc.haui.tuyensinh.viewmodel.HomeViewModel

/**
 * Màn hình hiển thị toàn bộ danh sách Tin Tức.
 * Được mở từ nút "Xem tất cả →" trong HomeFragment.
 *
 * Tái sử dụng HomeViewModel (đã load data) và TinTucAdapter (có DiffUtil + click).
 */
class TinTucListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTinTucListBinding
    private lateinit var adapter: TinTucAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTinTucListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.haui_red)
        binding.toolbar.setNavigationOnClickListener { finish() }

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = TinTucAdapter()
        binding.rvTinTuc.layoutManager = LinearLayoutManager(this)
        binding.rvTinTuc.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(this) { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }

        viewModel.tinTucList.observe(this) { list ->
            if (list.isNullOrEmpty()) {
                binding.tvEmpty.visibility = View.VISIBLE
                binding.rvTinTuc.visibility = View.GONE
            } else {
                binding.tvEmpty.visibility = View.GONE
                binding.rvTinTuc.visibility = View.VISIBLE
                adapter.submitList(list)
                binding.toolbar.subtitle = "${list.size} bài viết"
            }
        }

        viewModel.error.observe(this) { msg ->
            if (!msg.isNullOrEmpty()) {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
