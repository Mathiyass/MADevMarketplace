package com.madev.marketplace.ui.downloads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madev.marketplace.MADevApp
import com.madev.marketplace.domain.model.OrderItem
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DownloadsViewModel : ViewModel() {

    private val _items = MutableStateFlow<List<OrderItem>>(emptyList())
    val items: StateFlow<List<OrderItem>> = _items.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init { loadItems() }

    fun loadItems() {
        viewModelScope.launch {
            _isLoading.value = true
            MADevApp.orderRepository.getUserOrderItems()
                .onSuccess { _items.value = it }
            _isLoading.value = false
        }
    }
}
