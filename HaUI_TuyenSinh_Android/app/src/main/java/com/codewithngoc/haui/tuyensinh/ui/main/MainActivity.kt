package com.codewithngoc.haui.tuyensinh.ui.main
import com.codewithngoc.haui.tuyensinh.*
import com.codewithngoc.haui.tuyensinh.ui.chat.AiChatActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codewithngoc.haui.tuyensinh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.haui_red)

        // Floating Action Button - AI Chat
        binding.fabAiChatbot.setOnClickListener {
            startActivity(Intent(this, AiChatActivity::class.java))
        }

        // Setup BottomNavigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_news -> {
                    replaceFragment(NganhHocFragment())
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(HoSoFragment())
                    true
                }
                else -> false
            }
        }

        // Load default fragment
        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.nav_home
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}


