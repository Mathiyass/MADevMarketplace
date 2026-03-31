package com.madev.marketplace.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madev.marketplace.MADevApp
import com.madev.marketplace.domain.model.UserProfile
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _profile = MutableStateFlow<UserProfile?>(null)
    val profile: StateFlow<UserProfile?> = _profile.asStateFlow()

    init { loadProfile() }

    fun loadProfile() {
        viewModelScope.launch {
            MADevApp.authRepository.getUserProfile()
                .onSuccess { _profile.value = it }
        }
    }

    fun signOut(onDone: () -> Unit) {
        viewModelScope.launch {
            MADevApp.authRepository.logout()
            onDone()
        }
    }
}
