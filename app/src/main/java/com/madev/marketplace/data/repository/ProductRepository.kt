package com.madev.marketplace.data.repository

import com.madev.marketplace.data.local.dao.ProductDao
import com.madev.marketplace.data.local.entity.ProductEntity
import com.madev.marketplace.data.remote.ProductApi
import com.madev.marketplace.domain.model.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(
    private val productApi: ProductApi,
    private val productDao: ProductDao
) {
    fun getCachedProducts(): Flow<List<ProductEntity>> = productDao.getAllProducts()

    fun getCachedProductsByCategory(category: String): Flow<List<ProductEntity>> =
        productDao.getProductsByCategory(category)

    fun searchCachedProducts(query: String): Flow<List<ProductEntity>> =
        productDao.searchProducts(query)

    suspend fun refreshProducts() {
        val products = productApi.getProducts()
        productDao.insertAll(products.map { it.toEntity() })
    }

    suspend fun refreshProductsByCategory(category: String) {
        val products = productApi.getProductsByCategory(category)
        productDao.insertAll(products.map { it.toEntity() })
    }

    suspend fun getProductById(id: String): Product {
        return productApi.getProductById(id)
    }

    suspend fun searchProducts(query: String): List<Product> {
        return productApi.searchProducts(query)
    }

    private fun Product.toEntity() = ProductEntity(
        productId = productId,
        title = title,
        description = description,
        price = price,
        storagePath = storagePath,
        previewCode = previewCode,
        sizeMb = sizeMb,
        category = category,
        version = version,
        tags = tags?.joinToString(","),
        isActive = isActive,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
