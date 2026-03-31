package com.madev.marketplace.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.madev.marketplace.R
import com.madev.marketplace.databinding.ActivityOnboardingBinding
import com.madev.marketplace.databinding.ItemOnboardingPageBinding

data class OnboardingPage(val title: String, val desc: String, val imageRes: Int)

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private val pages = listOf(
        OnboardingPage("MARKETPLACE_ACCESS::", "Your gateway to premium Local AI models and specialized scripts.", R.drawable.ic_launcher_foreground),
        OnboardingPage("SECURE_DELIVERY::", "End-to-end encrypted downloads and instant automated licensing.", R.drawable.ic_launcher_foreground),
        OnboardingPage("EXPERT_NETWORK::", "Connect with elite local developers at exclusive meetups.", R.drawable.ic_launcher_foreground)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = OnboardingAdapter(pages)
        
        binding.btnNext.setOnClickListener {
            val current = binding.viewPager.currentItem
            if (current < pages.size - 1) {
                binding.viewPager.currentItem = current + 1
            } else {
                finishOnboarding()
            }
        }
        
        binding.viewPager.registerOnPageChangeCallback(object : androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.btnNext.text = if (position == pages.size - 1) "GET_STARTED::" else "NEXT_SEQUENCE::"
            }
        })
    }

    private fun finishOnboarding() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

    inner class OnboardingAdapter(private val items: List<OnboardingPage>) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {
        inner class ViewHolder(val binding: ItemOnboardingPageBinding) : RecyclerView.ViewHolder(binding.root)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            ItemOnboardingPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val page = items[position]
            holder.binding.txtTitle.text = page.title
            holder.binding.txtDesc.text = page.desc
            holder.binding.imgOnboarding.setImageResource(page.imageRes)
        }
        override fun getItemCount() = items.size
    }
}
