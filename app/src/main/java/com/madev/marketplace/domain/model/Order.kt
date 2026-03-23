package com.madev.marketplace.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Order(
    @SerialName("order_id") val orderId: String = "",
    @SerialName("user_id") val userId: String = "",
    @SerialName("total_amount") val totalAmount: Double = 0.0,
    @SerialName("payment_status") val paymentStatus: String = "pending",
    @SerialName("payment_ref") val paymentRef: String? = null,
    @SerialName("order_date") val orderDate: String? = null
)

@Serializable
data class OrderItem(
    @SerialName("item_id") val itemId: String = "",
    @SerialName("order_id") val orderId: String = "",
    @SerialName("product_id") val productId: String = "",
    @SerialName("price_paid") val pricePaid: Double? = null,
    @SerialName("license_key") val licenseKey: String? = null
)
