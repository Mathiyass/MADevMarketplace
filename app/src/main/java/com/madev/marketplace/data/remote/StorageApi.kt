package com.madev.marketplace.data.remote

import io.github.jan.supabase.functions.functions
import io.ktor.client.call.*
import io.ktor.http.*
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class StorageApi {

    private val client = SupabaseClient.client

    suspend fun getSignedDownloadUrl(productId: String): String {
        val response = client.functions.invoke(
            function = "generate-signed-url",
            body = buildJsonObject {
                put("product_id", productId)
            }
        )
        val body = response.body<String>()
        val json = kotlinx.serialization.json.Json.parseToJsonElement(body)
        return json.jsonObject["url"]?.toString()?.removeSurrounding("\"") ?: ""
    }
}
