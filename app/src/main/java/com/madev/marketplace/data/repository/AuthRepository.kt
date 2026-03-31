package com.madev.marketplace.data.repository

import com.madev.marketplace.MADevApp
import com.madev.marketplace.domain.model.UserProfile
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class AuthRepository {

    private val auth get() = MADevApp.supabase.auth
    private val db get() = MADevApp.supabase.postgrest

    suspend fun login(email: String, password: String): Result<Unit> = runCatching {
        auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun register(email: String, password: String, fullName: String): Result<Unit> = runCatching {
        auth.signUpWith(Email) {
            this.email = email
            this.password = password
            data = buildJsonObject { put("full_name", fullName) }
        }
    }

    suspend fun logout(): Result<Unit> = runCatching {
        auth.signOut()
    }

    fun getCurrentUser() = auth.currentUserOrNull()

    suspend fun getUserProfile(): Result<UserProfile> = runCatching {
        val uid = auth.currentUserOrNull()?.id ?: throw Exception("Not authenticated")
        db.from("profiles").select {
            filter { eq("user_id", uid) }
        }.decodeSingle<UserProfile>()
    }

    suspend fun updateFcmToken(token: String) {
        try {
            val uid = auth.currentUserOrNull()?.id ?: return
            db.from("profiles").upsert(
                mapOf("user_id" to uid, "fcm_token" to token)
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
