package com.madev.marketplace.ui.splash

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.madev.marketplace.R
import com.madev.marketplace.data.remote.SupabaseClient
import com.madev.marketplace.ui.MainActivity
import com.madev.marketplace.ui.auth.AuthActivity
import com.madev.marketplace.ui.onboarding.OnboardingActivity
import com.madev.marketplace.util.SessionManager
import io.github.jan.supabase.auth.auth

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sessionManager = SessionManager(this)

        val cursorView = findViewById<TextView>(R.id.tvCursor)
        val titleView = findViewById<TextView>(R.id.tvTitle)
        val subtitleView = findViewById<TextView>(R.id.tvSubtitle)

        // Blinking cursor animation
        val blinkAnim = AlphaAnimation(1f, 0f).apply {
            duration = 500
            repeatMode = Animation.REVERSE
            repeatCount = Animation.INFINITE
        }
        cursorView.startAnimation(blinkAnim)

        // Typing animation for subtitle
        val fullText = "initializing system..."
        subtitleView.text = ""
        var charIndex = 0
        val handler = Handler(Looper.getMainLooper())
        val typeWriter = object : Runnable {
            override fun run() {
                if (charIndex < fullText.length) {
                    subtitleView.text = fullText.substring(0, charIndex + 1)
                    charIndex++
                    handler.postDelayed(this, 80)
                }
            }
        }
        handler.postDelayed(typeWriter, 300)

        // Navigate after 2 seconds
        handler.postDelayed({
            navigateNext()
        }, 2000)

        // Skip on tap
        findViewById<android.view.View>(R.id.splashRoot).setOnClickListener {
            handler.removeCallbacksAndMessages(null)
            navigateNext()
        }
    }

    private fun navigateNext() {
        val currentSession = SupabaseClient.client.auth.currentSessionOrNull()
        val intent = when {
            currentSession != null -> Intent(this, MainActivity::class.java)
            !sessionManager.isOnboardingComplete -> Intent(this, OnboardingActivity::class.java)
            else -> Intent(this, AuthActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
