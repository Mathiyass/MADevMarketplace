package com.madev.marketplace.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madev.marketplace.MADevApp
import com.madev.marketplace.domain.model.*
import com.madev.marketplace.domain.usecase.GetSignedUrlUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductDetailViewModel : ViewModel() {
    private val getSignedUrlUseCase = GetSignedUrlUseCase(MADevApp.productRepository)

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product.asStateFlow()

    private val _hasPurchased = MutableStateFlow(false)
    val hasPurchased: StateFlow<Boolean> = _hasPurchased.asStateFlow()

    private val _signedUrl = MutableStateFlow<SignedUrlResponse?>(null)
    val signedUrl: StateFlow<SignedUrlResponse?> = _signedUrl.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadProduct(productId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            MADevApp.productRepository.getProductById(productId)
                .onSuccess {
                    _product.value = it
                    _hasPurchased.value = MADevApp.orderRepository.hasPurchased(productId)
                }
                .onFailure { _error.value = it.message }
            _isLoading.value = false
        }
    }

    fun createOrder(productId: String, amount: Double, onCreated: (com.madev.marketplace.domain.model.Order) -> Unit) {
        viewModelScope.launch {
            MADevApp.orderRepository.createOrder(productId, amount)
                .onSuccess { onCreated(it) }
                .onFailure { _error.value = it.message }
        }
    }

    fun getSignedUrl(productId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            getSignedUrlUseCase(productId)
                .onSuccess { _signedUrl.value = it }
                .onFailure { _error.value = it.message }
            _isLoading.value = false
        }
    }
}
