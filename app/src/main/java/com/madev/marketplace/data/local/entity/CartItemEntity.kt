package com.madev.marketplace.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey val productId: String,
    val title: String,
    val price: Double,
    val category: String,
    val addedAt: Long = System.currentTimeMillis()
)
