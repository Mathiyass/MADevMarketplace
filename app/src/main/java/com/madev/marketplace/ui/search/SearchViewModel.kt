package com.madev.marketplace.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madev.marketplace.MADevApp
import com.madev.marketplace.domain.model.Product
import com.madev.marketplace.domain.usecase.SearchProductsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val searchProductsUseCase = SearchProductsUseCase(MADevApp.productRepository)

    private val _results = MutableStateFlow<List<Product>>(emptyList())
    val results: StateFlow<List<Product>> = _results.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun search(query: String) {
        if (query.isBlank()) { 
            _results.value = emptyList()
            return 
        }
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _results.value = searchProductsUseCase(query)
            } catch (e: Exception) {
                _results.value = emptyList()
            }
            _isLoading.value = false
        }
    }
}
