package com.madev.marketplace

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.functions.Functions
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage

class MADevApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            listOf(
                NotificationChannel(CHANNEL_DOWNLOADS, getString(R.string.channel_downloads), NotificationManager.IMPORTANCE_LOW).apply {
                    description = "File download progress"
                },
                NotificationChannel(CHANNEL_UPDATES, getString(R.string.channel_updates), NotificationManager.IMPORTANCE_DEFAULT).apply {
                    description = "Product update notifications"
                },
                NotificationChannel(CHANNEL_GENERAL, getString(R.string.channel_general), NotificationManager.IMPORTANCE_DEFAULT).apply {
                    description = "General announcements"
                }
            ).forEach { nm.createNotificationChannel(it) }
        }
    }

    companion object {
        lateinit var instance: MADevApp
            private set

        const val CHANNEL_DOWNLOADS = "downloads"
        const val CHANNEL_UPDATES   = "updates"
        const val CHANNEL_GENERAL   = "general"

        // Database
        private val database by lazy { AppDatabase.getDatabase(instance) }

        // Repositories
        val authRepository by lazy { AuthRepository() }
        val productRepository by lazy { ProductRepository() }
        val orderRepository by lazy { OrderRepository() }
        val cartRepository by lazy { CartRepository(database) }

        // Singleton Supabase client
        val supabase by lazy {
            createSupabaseClient(
                supabaseUrl  = BuildConfig.SUPABASE_URL,
                supabaseKey  = BuildConfig.SUPABASE_ANON_KEY
            ) {
                install(Auth)
                install(Postgrest)
                install(Storage)
                install(Functions)
                install(Realtime)
            }
        }
    }
}
