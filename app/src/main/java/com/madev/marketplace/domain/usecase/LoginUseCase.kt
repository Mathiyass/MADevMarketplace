package com.madev.marketplace.domain.usecase

import com.madev.marketplace.data.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) {
        require(email.isNotBlank()) { "Email is required" }
        require(password.length >= 6) { "Password must be at least 6 characters" }
        authRepository.signIn(email, password)
    }
}

class RegisterUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String, fullName: String) {
        require(email.isNotBlank()) { "Email is required" }
        require(password.length >= 6) { "Password must be at least 6 characters" }
        require(fullName.isNotBlank()) { "Full name is required" }
        authRepository.signUp(email, password, fullName)
    }
}
