package com.madev.marketplace.data.remote

import com.madev.marketplace.domain.model.User
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class ProfileApi {

    private val client = SupabaseClient.client

    suspend fun getProfile(userId: String): User {
        return client.postgrest.from("profiles")
            .select {
                filter { eq("user_id", userId) }
            }
            .decodeSingle()
    }

    suspend fun updateProfile(userId: String, fullName: String?, avatarUrl: String?) {
        client.postgrest.from("profiles")
            .update(buildJsonObject {
                fullName?.let { put("full_name", it) }
                avatarUrl?.let { put("avatar_url", it) }
            }) {
                filter { eq("user_id", userId) }
            }
    }

    suspend fun updateOneSignalId(userId: String, oneSignalId: String) {
        client.postgrest.from("profiles")
            .update(buildJsonObject {
                put("onesignal_id", oneSignalId)
            }) {
                filter { eq("user_id", userId) }
            }
    }
}
