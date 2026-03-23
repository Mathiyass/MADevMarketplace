package com.madev.marketplace.data.repository

import com.madev.marketplace.data.remote.SupabaseClient
import com.madev.marketplace.util.SessionManager
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.providers.builtin.IDToken
import io.github.jan.supabase.auth.providers.Github
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class AuthRepository(private val sessionManager: SessionManager) {

    private val auth = SupabaseClient.client.auth

    suspend fun signIn(email: String, password: String) {
        auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
        saveSession()
    }

    suspend fun signUp(email: String, password: String, fullName: String) {
        auth.signUpWith(Email) {
            this.email = email
            this.password = password
            data = buildJsonObject {
                put("full_name", fullName)
            }
        }
    }

    suspend fun signInWithGithub() {
        auth.signInWith(Github)
    }

    suspend fun signOut() {
        auth.signOut()
        sessionManager.clearSession()
    }

    fun getCurrentUserId(): String? {
        return auth.currentUserOrNull()?.id
    }

    fun getCurrentUserEmail(): String? {
        return auth.currentUserOrNull()?.email
    }

    fun isLoggedIn(): Boolean {
        return auth.currentSessionOrNull() != null
    }

    private fun saveSession() {
        val user = auth.currentUserOrNull()
        sessionManager.userId = user?.id
        sessionManager.userEmail = user?.email
        sessionManager.userName = user?.userMetadata?.get("full_name")?.toString()?.removeSurrounding("\"")
    }
}
