package com.madev.marketplace

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.madev.marketplace.util.Constants
import com.onesignal.OneSignal

class MADevApp : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
        initOneSignal()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val downloadChannel = NotificationChannel(
                Constants.DOWNLOAD_CHANNEL_ID,
                "Downloads",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Shows download progress for purchased assets"
            }
            val nm = getSystemService(NotificationManager::class.java)
            nm.createNotificationChannel(downloadChannel)
        }
    }

    private fun initOneSignal() {
        OneSignal.initWithContext(this, Constants.ONESIGNAL_APP_ID)
    }
}
