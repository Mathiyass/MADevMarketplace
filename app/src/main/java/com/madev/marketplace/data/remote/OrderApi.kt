package com.madev.marketplace.data.remote

import com.madev.marketplace.domain.model.Order
import com.madev.marketplace.domain.model.OrderItem
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class OrderApi {

    private val client = SupabaseClient.client

    suspend fun getUserOrders(userId: String): List<Order> {
        return client.postgrest.from("orders")
            .select {
                filter { eq("user_id", userId) }
            }
            .decodeList()
    }

    suspend fun getOrderItems(orderId: String): List<OrderItem> {
        return client.postgrest.from("order_items")
            .select {
                filter { eq("order_id", orderId) }
            }
            .decodeList()
    }

    suspend fun getUserPurchasedItems(userId: String): List<OrderItem> {
        return client.postgrest.from("order_items")
            .select(columns = io.github.jan.supabase.postgrest.query.Columns.raw(
                "*, orders!inner(user_id, payment_status)"
            )) {
                filter {
                    eq("orders.user_id", userId)
                    eq("orders.payment_status", "paid")
                }
            }
            .decodeList()
    }

    suspend fun createOrder(userId: String, totalAmount: Double): Order {
        return client.postgrest.from("orders")
            .insert(buildJsonObject {
                put("user_id", userId)
                put("total_amount", totalAmount)
                put("payment_status", "pending")
            })
            .decodeSingle()
    }

    suspend fun createOrderItem(orderId: String, productId: String, pricePaid: Double): OrderItem {
        return client.postgrest.from("order_items")
            .insert(buildJsonObject {
                put("order_id", orderId)
                put("product_id", productId)
                put("price_paid", pricePaid)
            })
            .decodeSingle()
    }

    suspend fun getOrderById(orderId: String): Order {
        return client.postgrest.from("orders")
            .select {
                filter { eq("order_id", orderId) }
            }
            .decodeSingle()
    }
}
