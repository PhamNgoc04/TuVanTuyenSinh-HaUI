package com.codewithngoc.haui.tuyensinh.ui.main
import com.codewithngoc.haui.tuyensinh.*

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.codewithngoc.haui.tuyensinh.databinding.FragmentHoSoBinding
import com.codewithngoc.haui.tuyensinh.ui.auth.LoginActivity
import com.codewithngoc.haui.tuyensinh.ui.info.ThongTinThietYeuActivity
import com.codewithngoc.haui.tuyensinh.ui.profile.EditProfileActivity
import com.codewithngoc.haui.tuyensinh.ui.admin.AdminDashboardActivity
import com.codewithngoc.haui.tuyensinh.viewmodel.ProfileViewModel

class HoSoFragment : Fragment() {

    private var _binding: FragmentHoSoBinding? = null
    private val binding get() = _binding!!
    private var accountId = ""
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoSoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        val prefs = requireActivity().getSharedPreferences("haui_prefs", Context.MODE_PRIVATE)
        accountId = prefs.getString("accountId", "") ?: ""

        setupObservers()

        if (accountId.isNotEmpty()) {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.loadProfile(accountId)
        } else {
            binding.tvName.text = "Xin chào, ${prefs.getString("username", "User")}"
        }

        binding.btnThietYeu.setOnClickListener {
            startActivity(Intent(requireContext(), ThongTinThietYeuActivity::class.java))
        }

        binding.btnEditProfile.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            val userPrefs = requireActivity().getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
            prefs.edit().clear().apply()
            userPrefs.edit().clear().apply()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun setupObservers() {
        viewModel.userProfile.observe(viewLifecycleOwner) { user ->
            binding.progressBar.visibility = View.GONE
            if (user != null) {
                binding.tvName.text = user.ten ?: "Báo danh thành công"
                binding.tvRole.text = "Vai trò: ${user.vaiTro ?: "USER"}\nEmail: ${user.email ?: "Chưa có"}"
                
                if (user.vaiTro == "ADMIN") {
                    binding.btnAdmin.visibility = View.VISIBLE
                    binding.btnAdmin.setOnClickListener {
                        startActivity(Intent(requireContext(), AdminDashboardActivity::class.java))
                    }
                } else {
                    binding.btnAdmin.visibility = View.GONE
                }
            } else {
                Toast.makeText(context, "Lỗi tải hồ sơ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (accountId.isNotEmpty()) {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.loadProfile(accountId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
