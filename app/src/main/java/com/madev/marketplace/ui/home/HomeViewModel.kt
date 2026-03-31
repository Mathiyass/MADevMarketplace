package com.madev.marketplace.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madev.marketplace.MADevApp
import com.madev.marketplace.domain.model.*
import com.madev.marketplace.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val getProductsUseCase = GetProductsUseCase(MADevApp.productRepository)

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private var selectedCategory: ProductCategory? = null

    init { loadProducts() }

    fun loadProducts(category: ProductCategory? = null) {
        selectedCategory = category
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            getProductsUseCase(category)
                .catch { _error.value = it.message }
                .collect { _products.value = it }
            _isLoading.value = false
        }
    }

    fun refresh() = loadProducts(selectedCategory)
}
