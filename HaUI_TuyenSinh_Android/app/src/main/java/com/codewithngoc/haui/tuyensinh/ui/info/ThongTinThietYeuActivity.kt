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
import android.widget.ScrollView
import android.widget.LinearLayout
import com.google.android.material.card.MaterialCardView
import android.text.Html
import androidx.core.content.ContextCompat
import com.codewithngoc.haui.tuyensinh.R

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
        val scrollView = ScrollView(requireContext())
        scrollView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        scrollView.isFillViewport = true

        val llContainer = LinearLayout(requireContext())
        llContainer.orientation = LinearLayout.VERTICAL
        llContainer.setPadding(32, 48, 32, 48)
        scrollView.addView(llContainer)

        val createEmptyView = {
            val tv = TextView(requireContext())
            tv.text = "Chưa có thông tin cập nhật."
            tv.textSize = 15f
            tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_secondary))
            tv.setPadding(32, 64, 32, 32)
            tv.textAlignment = View.TEXT_ALIGNMENT_CENTER
            tv
        }

        fun addCard(icon: String, htmlTitle: String, subtitle: String) {
            val card = MaterialCardView(requireContext())
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(0, 0, 0, 32)
            card.layoutParams = params
            card.radius = 24f
            card.cardElevation = 4f
            card.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bg_card))

            val llInner = LinearLayout(requireContext())
            llInner.orientation = LinearLayout.HORIZONTAL
            llInner.setPadding(40, 40, 40, 40)
            llInner.gravity = android.view.Gravity.CENTER_VERTICAL

            val tvIcon = TextView(requireContext())
            tvIcon.text = icon
            tvIcon.textSize = 32f
            tvIcon.setPadding(0, 0, 32, 0)

            val llText = LinearLayout(requireContext())
            llText.orientation = LinearLayout.VERTICAL
            llText.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)

            val tvTitle = TextView(requireContext())
            tvTitle.text = Html.fromHtml(htmlTitle, Html.FROM_HTML_MODE_COMPACT)
            tvTitle.textSize = 16f
            tvTitle.setLineSpacing(0f, 1.2f)
            tvTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_primary))

            val tvSub = TextView(requireContext())
            tvSub.text = subtitle
            tvSub.textSize = 13f
            tvSub.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_secondary))
            tvSub.setPadding(0, 12, 0, 0)

            llText.addView(tvTitle)
            if (subtitle.isNotEmpty()) llText.addView(tvSub)

            llInner.addView(tvIcon)
            llInner.addView(llText)
            card.addView(llInner)
            llContainer.addView(card)
        }

        val position = arguments?.getInt(ARG_POSITION) ?: 0
        val viewModel = (requireActivity() as ThongTinThietYeuActivity).infoViewModel

        when (position) {
            0 -> {
                viewModel.hocPhiList.observe(viewLifecycleOwner) { list ->
                    llContainer.removeAllViews()
                    if (list.isNullOrEmpty()) {
                        llContainer.addView(createEmptyView())
                    } else {
                        list.forEach { 
                            addCard(
                                "💰", 
                                "<b>${it.soTien}</b>", 
                                "Năm áp dụng: ${it.namHoc}"
                            ) 
                        }
                    }
                }
            }
            1 -> {
                viewModel.hocBongList.observe(viewLifecycleOwner) { list ->
                    llContainer.removeAllViews()
                    if (list.isNullOrEmpty()) {
                        llContainer.addView(createEmptyView())
                    } else {
                        list.forEach { 
                            addCard(
                                "🏆", 
                                "<b>Học bổng:</b><br>${it.loaiHb}", 
                                "🎯 GPA: ${it.diemYc} | Hạnh kiểm: ${it.hanhKiemYc}"
                            ) 
                        }
                    }
                }
            }
            2 -> {
                viewModel.quyTrinhList.observe(viewLifecycleOwner) { list ->
                    llContainer.removeAllViews()
                    if (list.isNullOrEmpty()) {
                        llContainer.addView(createEmptyView())
                    } else {
                        list.forEachIndexed { index, quyTrinh -> 
                            val stepNum = index + 1
                            val iconNum = when(stepNum) {
                                1 -> "❶"; 2 -> "❷"; 3 -> "❸"; 4 -> "❹"; 5 -> "❺"; 6 -> "❻"
                                else -> "⭐️"
                            }
                            addCard(
                                iconNum, 
                                "<b>Bước ${stepNum}</b>", 
                                quyTrinh.noiDung ?: ""
                            ) 
                        }
                    }
                }
            }
            3 -> {
                viewModel.chuongTrinhList.observe(viewLifecycleOwner) { list ->
                    llContainer.removeAllViews()
                    if (list.isNullOrEmpty()) {
                        llContainer.addView(createEmptyView())
                    } else {
                        list.forEach { 
                            val tenDaoTao = if (it.tenChuongTrinh == "null" || it.tenChuongTrinh == null) "Chương trình Tiêu chuẩn" else it.tenChuongTrinh
                            val moTaDaoTao = if (it.moTa == "null" || it.moTa == null) "Chương trình đại học chính quy thiết kế theo chuẩn kỹ sư HaUI." else it.moTa
                            addCard(
                                "🎓", 
                                "<b>$tenDaoTao</b>", 
                                moTaDaoTao
                            ) 
                        }
                    }
                }
            }
        }
        return scrollView
    }
}
