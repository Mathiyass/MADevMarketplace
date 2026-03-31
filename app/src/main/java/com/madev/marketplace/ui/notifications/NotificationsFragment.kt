package com.madev.marketplace.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.madev.marketplace.databinding.FragmentDownloadsBinding

class NotificationsFragment : Fragment() {
    // Minimal stub — notifications are delivered via FCM to system tray
    // In-app log can be loaded from notifications_log table
    private var _binding: FragmentDownloadsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDownloadsBinding.inflate(inflater, container, false)
        binding.emptyState.visibility = View.VISIBLE
        binding.rvDownloads.visibility = View.GONE
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
