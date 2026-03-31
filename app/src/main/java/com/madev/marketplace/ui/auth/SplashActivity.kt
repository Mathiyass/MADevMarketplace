package com.madev.marketplace.ui.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.madev.marketplace.MADevApp
import com.madev.marketplace.databinding.ActivitySplashBinding
import com.madev.marketplace.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cursor Blink Animation
        val blink = AlphaAnimation(0.0f, 1.0f).apply {
            duration = 500
            startOffset = 20
            repeatMode = Animation.REVERSE
            repeatCount = Animation.INFINITE
        }
        binding.imgCursor.startAnimation(blink)

        // Start Typewriter
        animateText("MADEV_MARKETPLACE::INIT")

        Handler(Looper.getMainLooper()).postDelayed({
            checkAuthAndProceed()
        }, 3000)
    }

    private fun animateText(target: String) {
        var current = ""
        val handler = Handler(Looper.getMainLooper())
        target.forEachIndexed { index, char ->
            handler.postDelayed({
                current += char
                binding.txt_splash_title.text = "> $current"
            }, (index * 100).toLong())
        }
    }

    private fun checkAuthAndProceed() {
        val user = MADevApp.supabase.auth.currentUserOrNull()
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            // In a real app, check if onboarding was shown via SharedPreferences
            startActivity(Intent(this, AuthActivity::class.java))
        }
        finish()
    }
}
