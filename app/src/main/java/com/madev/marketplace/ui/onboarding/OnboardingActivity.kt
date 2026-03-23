package com.madev.marketplace.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.madev.marketplace.R
import com.madev.marketplace.ui.auth.AuthActivity
import com.madev.marketplace.util.SessionManager

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        sessionManager = SessionManager(this)

        viewPager = findViewById(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabIndicator)
        val btnGetStarted = findViewById<com.google.android.material.button.MaterialButton>(R.id.btnGetStarted)
        val btnSkip = findViewById<android.widget.TextView>(R.id.btnSkip)

        val pages = listOf(
            OnboardingPage(R.drawable.ic_smart_toy, "Local AI & Dev Tools.", "Download optimized models, debloated scripts, and productivity tools."),
            OnboardingPage(R.drawable.ic_download, "Background Downloads.", "Download massive files in the background. Auto-resumes on Wi-Fi."),
            OnboardingPage(R.drawable.ic_lock, "Secure. Licensed. Yours.", "Every purchase generates a unique license key stored in your library.")
        )

        viewPager.adapter = OnboardingAdapter(pages)
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                btnGetStarted.visibility = if (position == pages.size - 1) View.VISIBLE else View.GONE
                btnSkip.visibility = if (position < pages.size - 1) View.VISIBLE else View.GONE
            }
        })

        btnGetStarted.setOnClickListener { goToAuth() }
        btnSkip.setOnClickListener { goToAuth() }
    }

    private fun goToAuth() {
        sessionManager.isOnboardingComplete = true
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}

data class OnboardingPage(val iconRes: Int, val title: String, val subtitle: String)
