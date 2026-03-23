package com.madev.marketplace.data.repository

import com.madev.marketplace.data.local.dao.CartDao
import com.madev.marketplace.data.local.entity.CartItemEntity
import com.madev.marketplace.domain.model.Product
import kotlinx.coroutines.flow.Flow

class CartRepository(private val cartDao: CartDao) {
    fun getCartItems(): Flow<List<CartItemEntity>> = cartDao.getAllItems()
    fun getItemCount(): Flow<Int> = cartDao.getItemCount()
    fun getTotal(): Flow<Double?> = cartDao.getTotal()

    suspend fun addItem(product: Product) {
        cartDao.insert(CartItemEntity(
            productId = product.productId,
            title = product.title,
            price = product.price,
            category = product.category
        ))
    }

    suspend fun removeItem(productId: String) {
        cartDao.deleteByProductId(productId)
    }

    suspend fun clearCart() {
        cartDao.clearAll()
    }

    suspend fun isInCart(productId: String): Boolean {
        return cartDao.isInCart(productId)
    }
}
