package com.madev.marketplace.data.repository

import com.madev.marketplace.MADevApp
import com.madev.marketplace.domain.model.*
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OrderRepository {

    private val db get() = MADevApp.supabase.postgrest

    fun getUserOrders(): Flow<List<com.madev.marketplace.domain.model.Order>> = flow {
        val uid = MADevApp.supabase.auth.currentUserOrNull()?.id ?: throw Exception("Not authenticated")
        val result = db.from("orders").select {
            filter {
                eq("user_id", uid)
                eq("payment_status", "paid")
            }
            order("order_date", Order.DESCENDING)
        }.decodeList<com.madev.marketplace.domain.model.Order>()
        emit(result)
    }

    suspend fun createOrder(productId: String, amount: Double): Result<com.madev.marketplace.domain.model.Order> = runCatching {
        val uid = MADevApp.supabase.auth.currentUserOrNull()?.id ?: throw Exception("Not authenticated")
        val order = db.from("orders").insert(
            mapOf("user_id" to uid, "total_amount" to amount, "payment_status" to "pending")
        ) { select() }.decodeSingle<com.madev.marketplace.domain.model.Order>()
        
        db.from("order_items").insert(
            mapOf("order_id" to order.orderId, "product_id" to productId, "price_paid" to amount)
        )
        order
    }

    suspend fun hasPurchased(productId: String): Boolean {
        // ... previous implementation ...
        return try {
            val uid = MADevApp.supabase.auth.currentUserOrNull()?.id ?: return false
            val result = db.from("order_items").select(
                Columns.raw("item_id, orders!inner(user_id, payment_status)")
            ) {
                filter {
                    eq("product_id", productId)
                    eq("orders.user_id", uid)
                    eq("orders.payment_status", "paid")
                }
                limit(1)
            }.data
            result != "[]" && result.isNotEmpty()
        } catch (e: Exception) { false }
    }

    suspend fun getUserOrderItems(): Result<List<OrderItem>> = runCatching {
        val uid = MADevApp.supabase.auth.currentUserOrNull()?.id ?: throw Exception("Not authenticated")
        db.from("order_items").select(
            Columns.raw("*, orders!inner(user_id, payment_status)")
        ) {
            filter {
                eq("orders.user_id", uid)
                eq("orders.payment_status", "paid")
            }
        }.decodeList<OrderItem>()
    }

    suspend fun getSignedDownloadUrl(productId: String): Result<SignedUrlResponse> = runCatching {
        // ... previous implementation ...
        val p = MADevApp.productRepository.getProductById(productId).getOrThrow()
        val path = p.downloadPath ?: throw Exception("No download path found")
        val signed = MADevApp.supabase.storage.from("products").createSignedUrl(path, 60.minutes)
        SignedUrlResponse(signed, "${p.title.replace(" ", "_")}.zip")
    }
}
