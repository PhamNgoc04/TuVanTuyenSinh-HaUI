package com.codewithngoc.haui.tuyensinh.ui.info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.codewithngoc.haui.tuyensinh.databinding.ActivityThongTinThietYeuBinding
import com.codewithngoc.haui.tuyensinh.viewmodel.InfoViewModel
import com.google.android.material.tabs.TabLayoutMediator

class ThongTinThietYeuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThongTinThietYeuBinding
    lateinit var infoViewModel: InfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThongTinThietYeuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = android.graphics.Color.parseColor("#B71C1C")
        binding.toolbar.setNavigationOnClickListener { finish() }

        infoViewModel = ViewModelProvider(this)[InfoViewModel::class.java]

        val adapter = ThietYeuPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Học phí"
                1 -> "Học bổng"
                2 -> "Quy trình"
                3 -> "Đào tạo"
                else -> ""
            }
        }.attach()
    }

    inner class ThietYeuPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            return ThietYeuFragment.newInstance(position)
        }
    }
}

class ThietYeuFragment : Fragment() {

    companion object {
        private const val ARG_POSITION = "position"
        fun newInstance(position: Int): ThietYeuFragment {
            val fragment = ThietYeuFragment()
            val args = Bundle()
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val tv = TextView(requireContext())
        tv.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        tv.setPadding(32, 32, 32, 32)
        tv.textSize = 16f
        tv.text = "Đang tải dữ liệu..."

        val position = arguments?.getInt(ARG_POSITION) ?: 0
val viewModel = (requireActivity() as ThongTinThietYeuActivity).infoViewModel

        when (position) {
            0 -> {
                viewModel.hocPhiList.observe(viewLifecycleOwner) { list ->
                    if (list.isNullOrEmpty()) tv.text = "Chưa có thông tin cập nhật."
                    else tv.text = list.joinToString("\n\n") { "• Mức học phí: ${it.soTien}\n  Năm áp dụng: ${it.namHoc}" }
                }
            }
            1 -> {
                viewModel.hocBongList.observe(viewLifecycleOwner) { list ->
                    if (list.isNullOrEmpty()) tv.text = "Chưa có thông tin cập nhật."
                    else tv.text = list.joinToString("\n\n") { "• Học bổng: ${it.loaiHb}\n  Điểm yêu cầu: ${it.diemYc}\n  Hạnh kiểm: ${it.hanhKiemYc}" }
                }
            }
            2 -> {
                viewModel.quyTrinhList.observe(viewLifecycleOwner) { list ->
                    if (list.isNullOrEmpty()) tv.text = "Chưa có thông tin cập nhật."
                    else tv.text = list.mapIndexed { index, quyTrinh -> "Bước ${index + 1}:\n${quyTrinh.noiDung}" }.joinToString("\n\n")
                }
            }
            3 -> {
                viewModel.chuongTrinhList.observe(viewLifecycleOwner) { list ->
                    if (list.isNullOrEmpty()) tv.text = "Chưa có thông tin cập nhật."
                    else tv.text = list.joinToString("\n\n") { "• ${it.tenChuongTrinh}\n  Mô tả: ${it.moTa}" }
                }
            }
        }
        return tv
    }
}
