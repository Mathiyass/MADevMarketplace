package com.madev.marketplace.domain.model

import com.madev.marketplace.R
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ProductCategory(val displayName: String, val colorRes: Int) {
    Local_AI("Local AI", R.color.cat_local_ai),
    Scripts("Scripts", R.color.cat_scripts),
    Linux_ISO("Linux ISO", R.color.cat_linux_iso),
    Tools("Tools", R.color.cat_tools);
}

@Serializable
data class Product(
    @SerialName("product_id") val productId: String,
    val title: String,
    val description: String = "",
    val price: Double,
    @SerialName("storage_path") val storagePath: String? = null,
    @SerialName("preview_code") val previewCode: String? = null,
    @SerialName("size_mb") val sizeMb: Double = 0.0,
    val category: ProductCategory,
    val version: String = "1.0.0",
    val tags: List<String> = emptyList(),
    val requirements: String? = null,
    @SerialName("download_count") val downloadCount: Int = 0,
    @SerialName("is_active") val isActive: Boolean = true,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null
) {
    val formattedPrice: String get() = "LKR %.2f".format(price)
    val formattedSize: String get() = when {
        sizeMb >= 1000 -> "%.1f GB".format(sizeMb / 1024)
        sizeMb > 0     -> "%.0f MB".format(sizeMb)
        else            -> "—"
    }
}

@Serializable
data class Order(
    @SerialName("order_id") val orderId: String,
    @SerialName("user_id") val userId: String,
    @SerialName("total_amount") val totalAmount: Double,
    @SerialName("payment_status") val paymentStatus: String = "pending",
    @SerialName("payment_ref") val paymentRef: String? = null,
    val currency: String = "LKR",
    @SerialName("order_date") val orderDate: String? = null
)

@Serializable
data class OrderItem(
    @SerialName("item_id") val itemId: String,
    @SerialName("order_id") val orderId: String,
    @SerialName("product_id") val productId: String,
    @SerialName("price_paid") val pricePaid: Double,
    @SerialName("license_key") val licenseKey: String
)

@Serializable
data class UserProfile(
    @SerialName("user_id") val userId: String,
    @SerialName("full_name") val fullName: String = "",
    @SerialName("avatar_url") val avatarUrl: String? = null,
    @SerialName("fcm_token") val fcmToken: String? = null,
    @SerialName("total_spent") val totalSpent: Double = 0.0,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)

@Serializable
data class Meetup(
    @SerialName("meetup_id") val meetupId: String,
    val title: String,
    val description: String? = null,
    val lat: Double,
    val lng: Double,
    val location: String? = null,
    @SerialName("starts_at") val startsAt: String? = null,
    @SerialName("is_active") val isActive: Boolean = true,
    @SerialName("created_at") val createdAt: String? = null
)

@Serializable
data class SignedUrlResponse(
    val url: String,
    val filename: String,
    @SerialName("size_mb") val sizeMb: Double,
    val expires: String
)
