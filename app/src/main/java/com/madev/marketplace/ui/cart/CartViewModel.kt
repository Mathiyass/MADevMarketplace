package com.madev.marketplace.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madev.marketplace.MADevApp
import com.madev.marketplace.domain.model.Product
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed class CartState {
    object Loading : CartState()
    data class Success(val items: List<Product>, val total: Double) : CartState()
    object Empty : CartState()
}

class CartViewModel : ViewModel() {

    private val repository = MADevApp.cartRepository
    private val productRepo = MADevApp.productRepository

    private val _state = MutableStateFlow<CartState>(CartState.Loading)
    val state: StateFlow<CartState> = _state.asStateFlow()

    init { loadCart() }

    fun loadCart() {
        viewModelScope.launch {
            repository.getItems().collect { cartItems ->
                if (cartItems.isEmpty()) {
                    _state.value = CartState.Empty
                    return@collect
                }

                val products = cartItems.mapNotNull { 
                    productRepo.getProductById(it.productId).getOrNull()
                }
                
                val total = products.sumOf { it.price }
                _state.value = CartState.Success(products, total)
            }
        }
    }

    fun removeItem(productId: String) {
        viewModelScope.launch {
            repository.removeFromCart(productId)
            loadCart()
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
            _state.value = CartState.Empty
        }
    }
}
