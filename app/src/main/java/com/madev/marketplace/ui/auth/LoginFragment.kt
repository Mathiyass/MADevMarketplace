package com.madev.marketplace.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.madev.marketplace.MADevApp
import com.madev.marketplace.databinding.FragmentLoginBinding
import com.madev.marketplace.ui.main.MainActivity
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val email = binding.tilEmail.editText?.text.toString()
            val pass = binding.tilPassword.editText?.text.toString()

            if (email.isBlank() || pass.isBlank()) {
                Toast.makeText(requireContext(), "REQUIRED_FIELD_MISSING::", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                binding.btnLogin.isEnabled = false
                val result = MADevApp.authRepository.login(email, pass)
                if (result.isSuccess) {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                } else {
                    Toast.makeText(requireContext(), "AUTH_ERROR:: ${result.exceptionOrNull()?.message}", Toast.LENGTH_LONG).show()
                    binding.btnLogin.isEnabled = true
                }
            }
        }

        binding.txtGoToRegister.setOnClickListener {
            (requireActivity() as AuthActivity).showFragment(RegisterFragment())
        }
    }
}
