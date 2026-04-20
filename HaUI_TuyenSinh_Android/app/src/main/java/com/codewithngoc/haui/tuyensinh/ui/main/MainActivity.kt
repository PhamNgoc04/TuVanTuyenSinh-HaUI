package com.codewithngoc.haui.tuyensinh.ui.main
import com.codewithngoc.haui.tuyensinh.*

import android.content.Intent
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codewithngoc.haui.tuyensinh.databinding.ActivityMainBinding
import com.codewithngoc.haui.tuyensinh.ui.chat.AiChatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.haui_red)

        setupFab()
        setupBottomNavigation()

        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.nav_home
        }
    }

    private fun setupFab() {
        binding.fabAiChatbot.setOnClickListener {
            startActivity(Intent(this, AiChatActivity::class.java))
        }

        var dX = 0f
        var dY = 0f
        var isMoved = false

        binding.fabAiChatbot.setOnTouchListener { view, event ->
            when (event.actionMasked) {
                android.view.MotionEvent.ACTION_DOWN -> {
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY
                    isMoved = false
                    true
                }
                android.view.MotionEvent.ACTION_MOVE -> {
                    val newX = event.rawX + dX
                    val newY = event.rawY + dY
                    if (kotlin.math.abs(view.x - newX) > 10 || kotlin.math.abs(view.y - newY) > 10) {
                        isMoved = true
                    }
                    view.y = newY.coerceIn(0f, (binding.root.height - view.height).toFloat())
                    view.x = newX.coerceIn(0f, (binding.root.width - view.width).toFloat())
                    true
                }
                android.view.MotionEvent.ACTION_UP -> {
                    if (!isMoved) {
                        view.performClick()
                    } else {
                        // ✅ Fix #7: Snap về cạnh gần nhất sau khi thả
                        val screenWidth = binding.root.width.toFloat()
                        val margin = 24f
                        val snapX = if (view.x + view.width / 2 > screenWidth / 2) {
                            screenWidth - view.width - margin
                        } else {
                            margin
                        }
                        view.animate()
                            .x(snapX)
                            .setDuration(250)
                            .setInterpolator(DecelerateInterpolator())
                            .start()
                    }
                    true
                }
                else -> false
            }
        }
    }

    // ✅ Warn #5 Fix: Singleton fragments — show/hide thay vì new instance mỗi lần
    // Giữ nguyên Fragment state (scroll, data) khi đổi tab, API không gọi lại
    private val homeFragment = HomeFragment()
    private val nganhHocFragment = NganhHocFragment()
    private val hoSoFragment = HoSoFragment()
    private var activeFragment: Fragment = homeFragment

    private fun setupBottomNavigation() {
        // Thêm tất cả fragments ngay từ đầu, ẩn những cái không phải home
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainer, hoSoFragment).hide(hoSoFragment)
            add(R.id.fragmentContainer, nganhHocFragment).hide(nganhHocFragment)
            add(R.id.fragmentContainer, homeFragment)
        }.commit()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val target: Fragment = when (item.itemId) {
                R.id.nav_home    -> homeFragment
                R.id.nav_news    -> nganhHocFragment
                R.id.nav_profile -> hoSoFragment
                else             -> return@setOnItemSelectedListener false
            }
            if (target != activeFragment) {
                supportFragmentManager.beginTransaction()
                    .hide(activeFragment)
                    .show(target)
                    .commit()
                activeFragment = target
            }
            true
        }
    }
}
