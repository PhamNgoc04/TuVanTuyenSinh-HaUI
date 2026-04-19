package com.codewithngoc.haui.tuyensinh.ui.course
import com.codewithngoc.haui.tuyensinh.*

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
                binding.toolbar.subtitle = "Đại học Công nghiệp Hà Nội"
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
        val root = inflater.inflate(R.layout.fragment_nganh_detail_tab, container, false)
        val llContainer = root.findViewById<LinearLayout>(R.id.llContainer)
        
        val pos = arguments?.getInt("POSITION") ?: 0
        // ✅ Fix #9: Dùng activityViewModels() delegate thay vì cast Activity
        val viewModel: NganhViewModel by activityViewModels()

        fun addCard(title: String, desc: String, iconRes: Int) {
            val cardView = inflater.inflate(R.layout.item_detail_card, llContainer, false)
            cardView.findViewById<TextView>(R.id.tvTitle).text = title
            // ✅ Fix #8: Html.fromHtml version-safe
            cardView.findViewById<TextView>(R.id.tvDescription).text =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    Html.fromHtml(desc, Html.FROM_HTML_MODE_LEGACY)
                else
                    @Suppress("DEPRECATION") Html.fromHtml(desc)
            cardView.findViewById<ImageView>(R.id.ivIcon).setImageResource(iconRes)
            llContainer.addView(cardView)
        }

        fun showEmpty() {
            val tv = TextView(requireContext())
            tv.text = "Đang cập nhật dữ liệu mới nhất..."
            tv.textSize = 14f
            tv.setTextColor(androidx.core.content.ContextCompat.getColor(requireContext(), R.color.text_secondary))
            tv.setPadding(32, 64, 32, 32)
            tv.textAlignment = View.TEXT_ALIGNMENT_CENTER
            llContainer.addView(tv)
        }

        when(pos) {
            0 -> viewModel.nganhDetail.observe(viewLifecycleOwner) { detail ->
                 llContainer.removeAllViews()
                 if (detail == null) showEmpty()
                 else {
                     addCard("Mô tả ngành học", detail.moTa ?: "Trống", android.R.drawable.ic_menu_info_details)
                     addCard("Cơ sở đào tạo", detail.coSo ?: "Chưa cập nhật", android.R.drawable.ic_menu_mapmode)
                 }
            }
            1 -> viewModel.ngheNghiepList.observe(viewLifecycleOwner) { list ->
                 llContainer.removeAllViews()
                 if (list.isNullOrEmpty()) showEmpty()
                 else list.forEach { 
                     addCard(it.tenNghe ?: "Nghề nghiệp", "<b>Mức lương:</b> ${it.mucLuong}<br><b>Tình trạng:</b> ${it.tinhTrang}", android.R.drawable.ic_menu_myplaces)
                 }
            }
            2 -> viewModel.chiTieuList.observe(viewLifecycleOwner) { list ->
                 llContainer.removeAllViews()
                 if (list.isNullOrEmpty()) showEmpty()
                 else list.forEach { 
                     addCard("Năm tuyển sinh: ${it.nam}", "<b>Chỉ tiêu:</b> ${it.soLuong} sinh viên<br><b>Cách thức:</b> ${it.phuongThuc}", android.R.drawable.ic_menu_sort_by_size)
                 }
            }
            3 -> viewModel.yeuCauList.observe(viewLifecycleOwner) { list ->
                 llContainer.removeAllViews()
                 if (list.isNullOrEmpty()) showEmpty()
                 else list.forEach { 
                     addCard("Khối xét tuyển: ${it.khoi}", "<b>Điểm chuẩn:</b> <font color='#B71C1C'>${it.diemTong}</font> điểm", android.R.drawable.ic_menu_edit)
                 }
            }
            4 -> viewModel.tinChiList.observe(viewLifecycleOwner) { list ->
                 llContainer.removeAllViews()
                 if (list.isNullOrEmpty()) showEmpty()
                 else list.forEach { 
                     addCard("Tín chỉ (Đại học chính quy)", "<b>Học phí:</b> ${it.giaTien}", android.R.drawable.ic_menu_manage)
                 }
            }
        }
        return root
    }
}
