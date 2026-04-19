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
import androidx.activity.result.contract.ActivityResultContracts
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import androidx.appcompat.app.AppCompatDelegate

class HoSoFragment : Fragment() {

    private var _binding: FragmentHoSoBinding? = null
    private val binding get() = _binding!!
    private var accountId = ""
    private lateinit var viewModel: ProfileViewModel

    private val pickMedia = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            try {
                val inputStream: InputStream? = requireContext().contentResolver.openInputStream(uri)
                val file = File(requireContext().filesDir, "avatar_${accountId}.jpg")
                val outputStream = FileOutputStream(file)
                if (inputStream != null) {
                    inputStream.copyTo(outputStream)
                    inputStream.close()
                }
                outputStream.close()
                
                binding.imgAvatar.imageTintList = null
                binding.imgAvatar.setImageURI(null) // Clear cache effectively
                binding.imgAvatar.setImageURI(Uri.fromFile(file))
                
                viewModel.updateAvatar(accountId, "file://${file.absolutePath}")
            } catch (e: Exception) {
                Toast.makeText(context, "Lỗi tải ảnh", Toast.LENGTH_SHORT).show()
            }
        }
    }

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

        val prefs = requireActivity().getSharedPreferences(AppPrefs.PREF_MAIN, Context.MODE_PRIVATE)
        accountId = prefs.getString(AppPrefs.KEY_ACCOUNT_ID, "") ?: ""

        setupObservers()

        if (accountId.isNotEmpty()) {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.loadProfile(accountId)
        } else {
            binding.tvName.text = "Xin chào, ${prefs.getString(AppPrefs.KEY_USERNAME, "User")}"
        }

        binding.btnThietYeu.setOnClickListener {
            startActivity(Intent(requireContext(), ThongTinThietYeuActivity::class.java))
        }

        binding.btnEditProfile.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileActivity::class.java))
        }

        val themePrefs = requireActivity().getSharedPreferences(AppPrefs.PREF_THEME, Context.MODE_PRIVATE)
        binding.switchDarkMode.isChecked = themePrefs.getBoolean(AppPrefs.KEY_IS_DARK, false)
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            themePrefs.edit().putBoolean(AppPrefs.KEY_IS_DARK, isChecked).apply()
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }

        binding.imgAvatar.setOnClickListener {
            if (accountId.isNotEmpty()) {
                pickMedia.launch("image/*")
            }
        }

        binding.btnLogout.setOnClickListener {
            // ✅ Fix #10: Clear TẤT CẢ SharedPreferences khi logout
            requireActivity().getSharedPreferences(AppPrefs.PREF_MAIN, Context.MODE_PRIVATE).edit().clear().apply()
            requireActivity().getSharedPreferences(AppPrefs.PREF_THEME, Context.MODE_PRIVATE).edit().clear().apply()
            startActivity(android.content.Intent(requireContext(), com.codewithngoc.haui.tuyensinh.ui.auth.LoginActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun setupObservers() {
        viewModel.userProfile.observe(viewLifecycleOwner) { user ->
            binding.progressBar.visibility = View.GONE
            if (user != null) {
                binding.tvName.text = user.ten ?: "Sinh viên HaUI"
                val vaiTroUpper = user.vaiTro?.uppercase()?.trim()
                val roleLabel = when (vaiTroUpper) {
                    "ADMIN", "QUẢN TRỊ HỆ THỐNG", "QUẢN TRỊ VIÊN" -> "⚙️  Quản trị viên"
                    "SINH_VIEN", "SINH VIÊN" -> "🎓  Sinh viên"
                    else -> "👤  ${user.vaiTro ?: "Người dùng"}"
                }
                binding.tvRole.text = roleLabel
                binding.tvEmail.text = user.email ?: "email@student.haui.edu.vn"

                if (user.avatar?.startsWith("file://") == true) {
                    binding.imgAvatar.imageTintList = null
                    binding.imgAvatar.setImageURI(null)
                    binding.imgAvatar.setImageURI(Uri.parse(user.avatar))
                } else {
                    binding.imgAvatar.setImageResource(android.R.drawable.ic_menu_myplaces)
                    binding.imgAvatar.imageTintList = android.content.res.ColorStateList.valueOf(android.graphics.Color.WHITE)
                }

                if (vaiTroUpper == "ADMIN" || vaiTroUpper == "QUẢN TRỊ HỆ THỐNG" || vaiTroUpper == "QUẢN TRỊ VIÊN") {
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
