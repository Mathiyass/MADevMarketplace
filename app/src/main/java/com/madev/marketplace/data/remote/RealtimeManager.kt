package com.madev.marketplace.data.remote

import io.github.jan.supabase.realtime.*
import io.github.jan.supabase.realtime.PostgresAction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

class RealtimeManager {

    private val client = SupabaseClient.client

    suspend fun subscribeToProducts(
        onInsert: (Map<String, Any?>) -> Unit,
        onUpdate: (Map<String, Any?>) -> Unit
    ): RealtimeChannel {
        val channel = client.realtime.channel("products-changes")

        val changes = channel.postgresChangeFlow<PostgresAction>(schema = "public") {
            table = "products"
        }

        channel.subscribe()

        kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
            changes.collect { action ->
                when (action) {
                    is PostgresAction.Insert -> {
                        onInsert(emptyMap())
                    }
                    is PostgresAction.Update -> {
                        onUpdate(emptyMap())
                    }
                    else -> {}
                }
            }
        }

        return channel
    }

    suspend fun subscribeToOrderStatus(
        userId: String,
        onStatusChange: (String, String) -> Unit
    ): RealtimeChannel {
        val channel = client.realtime.channel("order-status-$userId")

        val changes = channel.postgresChangeFlow<PostgresAction>(schema = "public") {
            table = "orders"
            filter = "user_id=eq.$userId"
        }

        channel.subscribe()

        kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
            changes.collect { action ->
                when (action) {
                    is PostgresAction.Update -> {
                        onStatusChange("", "")
                    }
                    else -> {}
                }
            }
        }

        return channel
    }

    suspend fun unsubscribe(channel: RealtimeChannel) {
        channel.unsubscribe()
    }
}
