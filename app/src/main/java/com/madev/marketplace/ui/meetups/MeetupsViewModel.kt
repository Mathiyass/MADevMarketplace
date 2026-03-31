package com.madev.marketplace.ui.meetups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madev.marketplace.MADevApp
import com.madev.marketplace.domain.model.Meetup
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MeetupsViewModel : ViewModel() {

    private val _meetups = MutableStateFlow<List<Meetup>>(emptyList())
    val meetups: StateFlow<List<Meetup>> = _meetups.asStateFlow()

    init {
        viewModelScope.launch {
            MADevApp.productRepository.getMeetups()
                .onSuccess { _meetups.value = it }
        }
    }
}
