package com.madev.marketplace.domain.usecase

import com.madev.marketplace.data.repository.ProductRepository
import com.madev.marketplace.data.repository.AuthRepository
import com.madev.marketplace.domain.model.Product
import com.madev.marketplace.domain.model.ProductCategory
import com.madev.marketplace.domain.model.SignedUrlResponse
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(private val repository: ProductRepository) {
    operator fun invoke(category: ProductCategory? = null): Flow<List<Product>> {
        return if (category == null) repository.getProducts() else repository.getProductsByCategory(category)
    }
}

class SearchProductsUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(query: String): List<Product> {
        return repository.searchProducts(query)
    }
}

class GetSignedUrlUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(productId: String): Result<SignedUrlResponse> {
        return repository.getSignedUrl(productId)
    }
}

class GetLibraryUseCase(private val repository: ProductRepository) {
    operator fun invoke(): Flow<List<Product>> {
        return repository.getPurchasedProducts()
    }
}

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, pass: String): Result<Unit> {
        return repository.login(email, pass)
    }
}
