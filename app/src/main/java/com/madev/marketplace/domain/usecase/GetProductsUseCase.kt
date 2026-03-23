package com.madev.marketplace.domain.usecase

import com.madev.marketplace.data.repository.ProductRepository
import com.madev.marketplace.domain.model.Product

class GetProductsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(category: String? = null): List<Product> {
        return if (category != null) {
            productRepository.searchProducts(category)
        } else {
            productRepository.getProductById("") // will refresh
            emptyList()
        }
    }

    suspend fun refresh(category: String? = null) {
        if (category != null) {
            productRepository.refreshProductsByCategory(category)
        } else {
            productRepository.refreshProducts()
        }
    }
}
