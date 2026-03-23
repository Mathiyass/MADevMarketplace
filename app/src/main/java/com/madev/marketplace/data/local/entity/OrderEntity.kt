package com.madev.marketplace.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey val orderId: String,
    val userId: String,
    val totalAmount: Double,
    val paymentStatus: String,
    val paymentRef: String?,
    val orderDate: String?
)
