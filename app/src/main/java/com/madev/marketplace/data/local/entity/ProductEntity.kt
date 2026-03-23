package com.madev.marketplace.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val productId: String,
    val title: String,
    val description: String?,
    val price: Double,
    val storagePath: String?,
    val previewCode: String?,
    val sizeMb: Double?,
    val category: String,
    val version: String?,
    val tags: String?, // stored as comma-separated
    val isActive: Boolean,
    val createdAt: String?,
    val updatedAt: String?
)
