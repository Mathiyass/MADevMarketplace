package com.madev.marketplace.data.repository

import com.madev.marketplace.MADevApp
import com.madev.marketplace.domain.model.*
import io.github.jan.supabase.functions.functions
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.*

class ProductRepository {

    private val db get() = MADevApp.supabase.postgrest
    private val fn get() = MADevApp.supabase.functions

    fun getProducts(category: ProductCategory? = null): Flow<List<Product>> = flow {
        val result = db.from("products").select {
            filter { 
                eq("is_active", true)
                if (category != null) eq("category", category.name)
            }
            order("download_count", Order.DESCENDING)
        }.decodeList<Product>()
        emit(result)
    }

    suspend fun searchProducts(query: String): List<Product> {
        return db.from("products").select {
            filter {
                eq("is_active", true)
                ilike("title", "%$query%")
            }
        }.decodeList<Product>()
    }

    suspend fun getProductById(id: String): Result<Product> = runCatching {
        db.from("products").select {
            filter { eq("product_id", id) }
            limit(1)
        }.decodeSingle<Product>()
    }

    suspend fun getMeetups(): Result<List<Meetup>> = runCatching {
        db.from("meetups").select {
            filter { eq("is_active", true) }
        }.decodeList<Meetup>()
    }

    suspend fun getSignedUrl(productId: String): Result<SignedUrlResponse> = runCatching {
        val response = fn.invoke("generate-signed-url",
            body = buildJsonObject { put("product_id", productId) }
        )
        Json { ignoreUnknownKeys = true }.decodeFromString(response.bodyAsText())
    }

    // Placeholder for Room linkage
    fun getPurchasedProducts(): Flow<List<Product>> = flow {
        // Logic to combine Room with Supabase
        emit(emptyList())
    }
}
