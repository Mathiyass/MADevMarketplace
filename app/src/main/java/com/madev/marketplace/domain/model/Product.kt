package com.madev.marketplace.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("product_id") val productId: String = "",
    val title: String = "",
    val description: String? = null,
    val price: Double = 0.0,
    @SerialName("storage_path") val storagePath: String? = null,
    @SerialName("preview_code") val previewCode: String? = null,
    @SerialName("size_mb") val sizeMb: Double? = null,
    val category: String = "",
    val version: String? = null,
    val tags: List<String>? = null,
    @SerialName("is_active") val isActive: Boolean = true,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)
