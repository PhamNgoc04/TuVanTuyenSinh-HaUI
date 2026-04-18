package com.codewithngoc.haui.tuyensinh.ui.course
import com.codewithngoc.haui.tuyensinh.*

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.codewithngoc.haui.tuyensinh.databinding.ActivityNganhHocDetailBinding
import com.codewithngoc.haui.tuyensinh.viewmodel.NganhViewModel
import com.google.android.material.tabs.TabLayoutMediator

class NganhHocDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNganhHocDetailBinding
    private var maNganh: String = ""
    lateinit var nganhViewModel: NganhViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNganhHocDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = android.graphics.Color.parseColor("#B71C1C")
        binding.toolbar.setNavigationOnClickListener { finish() }

        maNganh = intent.getStringExtra("MA_NGANH") ?: ""
        
        nganhViewModel = ViewModelProvider(this)[NganhViewModel::class.java]

        setupObservers()

        val adapter = NganhDetailPagerAdapter(this, maNganh)
        binding.viewPager.adapter = adapter
        
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "Chi tiết"
                1 -> "Nghề nghiệp"
                2 -> "Chỉ tiêu"
                3 -> "Yêu cầu"
                4 -> "Tín chỉ"
                else -> ""
            }
        }.attach()

        if (maNganh.isNotEmpty()) {
            nganhViewModel.loadNganhDetailData(maNganh)
        }
    }

    private fun setupObservers() {
        nganhViewModel.isLoading.observe(this) { isLoad ->
            // binding.progressBar.visibility = if(isLoad) View.VISIBLE else View.GONE
        }
        
        nganhViewModel.nganhDetail.observe(this) { detail ->
            if (detail != null) {
                binding.toolbar.title = detail.tenNganh
            }
        }
    }

    inner class NganhDetailPagerAdapter(activity: AppCompatActivity, val maNganh: String) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 5

        override fun createFragment(position: Int): Fragment {
            return NganhDetailTabFragment.newInstance(position)
        }
    }
}

class NganhDetailTabFragment : Fragment() {

    companion object {
        fun newInstance(position: Int): NganhDetailTabFragment {
            val f = NganhDetailTabFragment()
            val args = Bundle()
            args.putInt("POSITION", position)
            f.arguments = args
            return f
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = TextView(requireContext())
        view.setPadding(32, 32, 32, 32)
        view.textSize = 16f
        view.text = "Loading..."
        
        val pos = arguments?.getInt("POSITION") ?: 0
        val viewModel = (requireActivity() as NganhHocDetailActivity).nganhViewModel

        when(pos) {
            0 -> viewModel.nganhDetail.observe(viewLifecycleOwner) { detail ->
                 if (detail == null) view.text = "Trống"
                 else view.text = "Tên ngành: ${detail.tenNganh}\nCơ sở: ${detail.coSo}\nMô tả: ${detail.moTa}"
            }
            1 -> viewModel.ngheNghiepList.observe(viewLifecycleOwner) { list ->
                 if (list.isNullOrEmpty()) view.text = "Chưa cập nhật"
                 else view.text = list.joinToString("\n\n") { "• Tên nghề: ${it.tenNghe}\n  Mức lương: ${it.mucLuong}\n  Tình trạng: ${it.tinhTrang}" }
            }
            2 -> viewModel.chiTieuList.observe(viewLifecycleOwner) { list ->
                 if (list.isNullOrEmpty()) view.text = "Chưa cập nhật"
                 else view.text = list.joinToString("\n\n") { "• Năm: ${it.nam}\n  Số lượng: ${it.soLuong}\n  Phương thức: ${it.phuongThuc}" }
            }
            3 -> viewModel.yeuCauList.observe(viewLifecycleOwner) { list ->
                 if (list.isNullOrEmpty()) view.text = "Chưa cập nhật"
                 else view.text = list.joinToString("\n\n") { "• Khối: ${it.khoi}\n  Điểm tổng: ${it.diemTong}" }
            }
            4 -> viewModel.tinChiList.observe(viewLifecycleOwner) { list ->
                 if (list.isNullOrEmpty()) view.text = "Chưa cập nhật"
                 else view.text = list.joinToString("\n\n") { "• Tín chỉ: ${it.id}\n  Giá tiền: ${it.giaTien}" }
            }
        }
        return view
    }
}
