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
import com.madev.marketplace.databinding.FragmentRegisterBinding
import com.madev.marketplace.ui.main.MainActivity
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val name = binding.tilName.editText?.text.toString()
            val email = binding.tilEmail.editText?.text.toString()
            val pass = binding.tilPassword.editText?.text.toString()

            if (email.isBlank() || pass.isBlank() || name.isBlank()) {
                Toast.makeText(requireContext(), "REQUIRED_FIELD_MISSING::", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                binding.btnRegister.isEnabled = false
                val result = MADevApp.authRepository.register(email, pass, name)
                if (result.isSuccess) {
                    Toast.makeText(requireContext(), "IDENTITY_ENROLLED::CHECK_EMAIL_VERIFICATION", Toast.LENGTH_LONG).show()
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                } else {
                    Toast.makeText(requireContext(), "REG_ERROR:: ${result.exceptionOrNull()?.message}", Toast.LENGTH_LONG).show()
                    binding.btnRegister.isEnabled = true
                }
            }
        }

        binding.txtGoToLogin.setOnClickListener {
            (requireActivity() as AuthActivity).showFragment(LoginFragment())
        }
    }
}
