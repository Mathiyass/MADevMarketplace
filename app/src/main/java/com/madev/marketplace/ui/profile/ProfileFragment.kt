package com.madev.marketplace.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.madev.marketplace.databinding.FragmentProfileBinding
import com.madev.marketplace.ui.auth.AuthActivity
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val vm: ProfileViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            vm.profile.collect { profile ->
                profile ?: return@collect
                binding.tvFullName.text = profile.fullName.ifBlank { "Developer" }
                binding.tvEmail.text = profile.userId.take(8) + "..."
                binding.tvAvatarInitials.text = (profile.fullName.ifBlank { "D" }).first().uppercaseChar().toString()
                binding.tvTotalSpent.text = "LKR %.2f".format(profile.totalSpent)
                
                binding.tvMemberSince.text = try {
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val out = SimpleDateFormat("MMM yyyy", Locale.getDefault())
                    out.format(sdf.parse(profile.createdAt!!.take(10))!!)
                } catch (e: Exception) { "---" }
            }
        }

        binding.btnSignOut.setOnClickListener {
            vm.signOut {
                startActivity(Intent(requireContext(), AuthActivity::class.java))
                requireActivity().finish()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
