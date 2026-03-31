package com.madev.marketplace.data.repository

import com.madev.marketplace.data.local.AppDatabase
import com.madev.marketplace.data.local.CartItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CartRepository(private val db: AppDatabase) {

    private val cartDao = db.cartDao()

    fun getItems(): Flow<List<CartItem>> = flow {
        emit(cartDao.getCartItems())
    }

    suspend fun addToCart(productId: String) {
        cartDao.addToCart(CartItem(productId))
    }

    suspend fun removeFromCart(productId: String) {
        cartDao.removeFromCart(productId)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }
}
