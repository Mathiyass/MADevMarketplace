package com.madev.marketplace.data.remote

import com.madev.marketplace.domain.model.Product
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class ProductApi {

    private val client = SupabaseClient.client

    suspend fun getProducts(): List<Product> {
        return client.postgrest.from("products")
            .select {
                filter { eq("is_active", true) }
            }
            .decodeList()
    }

    suspend fun getProductsByCategory(category: String): List<Product> {
        return client.postgrest.from("products")
            .select {
                filter {
                    eq("is_active", true)
                    eq("category", category)
                }
            }
            .decodeList()
    }

    suspend fun getProductById(id: String): Product {
        return client.postgrest.from("products")
            .select {
                filter { eq("product_id", id) }
            }
            .decodeSingle()
    }

    suspend fun searchProducts(query: String): List<Product> {
        return client.postgrest.from("products")
            .select {
                filter {
                    eq("is_active", true)
                    or {
                        ilike("title", "%$query%")
                        ilike("description", "%$query%")
                    }
                }
            }
            .decodeList()
    }
}
