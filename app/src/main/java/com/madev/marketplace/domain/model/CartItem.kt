package com.madev.marketplace.domain.model

data class CartItem(
    val productId: String,
    val title: String,
    val price: Double,
    val category: String
)
