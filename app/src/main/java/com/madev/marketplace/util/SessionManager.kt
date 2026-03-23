package com.madev.marketplace.util

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SessionManager(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "madev_secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    var isOnboardingComplete: Boolean
        get() = prefs.getBoolean(Constants.PREF_ONBOARDING_COMPLETE, false)
        set(value) = prefs.edit().putBoolean(Constants.PREF_ONBOARDING_COMPLETE, value).apply()

    var sessionToken: String?
        get() = prefs.getString(Constants.PREF_SESSION_TOKEN, null)
        set(value) = prefs.edit().putString(Constants.PREF_SESSION_TOKEN, value).apply()

    var userEmail: String?
        get() = prefs.getString("user_email", null)
        set(value) = prefs.edit().putString("user_email", value).apply()

    var userId: String?
        get() = prefs.getString("user_id", null)
        set(value) = prefs.edit().putString("user_id", value).apply()

    var userName: String?
        get() = prefs.getString("user_name", null)
        set(value) = prefs.edit().putString("user_name", value).apply()

    fun clearSession() {
        prefs.edit()
            .remove(Constants.PREF_SESSION_TOKEN)
            .remove("user_email")
            .remove("user_id")
            .remove("user_name")
            .apply()
    }
}
