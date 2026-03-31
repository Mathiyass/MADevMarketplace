package com.madev.marketplace.service

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.madev.marketplace.MADevApp
import com.madev.marketplace.R
import kotlinx.coroutines.*
import java.io.File
import java.net.URL

class DownloadService : Service() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var notifId = 1001

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val url      = intent?.getStringExtra(EXTRA_URL)      ?: return START_NOT_STICKY
        val filename = intent.getStringExtra(EXTRA_FILENAME) ?: "download.bin"

        startForeground(notifId, buildNotification(filename, 0))

        scope.launch {
            try {
                val outputDir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
                    ?: cacheDir.resolve("MADev").also { it.mkdirs() }
                val file = File(outputDir, filename)

                val connection = URL(url).openConnection()
                connection.connect()
                val total = connection.contentLength.toLong()
                var downloaded = 0L

                connection.getInputStream().use { input ->
                    file.outputStream().use { output ->
                        val buffer = ByteArray(8192)
                        var bytes: Int
                        while (input.read(buffer).also { bytes = it } != -1) {
                            output.write(buffer, 0, bytes)
                            downloaded += bytes
                            val progress = if (total > 0) ((downloaded * 100) / total).toInt() else -1
                            updateNotification(filename, progress)
                        }
                    }
                }

                completeNotification(filename, file)
            } catch (e: Exception) {
                errorNotification(filename, e.message ?: "Download failed")
            } finally {
                stopSelf(startId)
            }
        }
        return START_NOT_STICKY
    }

    private fun buildNotification(filename: String, progress: Int): Notification {
        return NotificationCompat.Builder(this, MADevApp.CHANNEL_DOWNLOADS)
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .setContentTitle("Downloading")
            .setContentText(filename)
            .setProgress(100, progress, progress == -1)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun updateNotification(filename: String, progress: Int) {
        val n = buildNotification(filename, progress)
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).notify(notifId, n)
    }

    private fun completeNotification(filename: String, file: File) {
        val n = NotificationCompat.Builder(this, MADevApp.CHANNEL_DOWNLOADS)
            .setSmallIcon(android.R.drawable.stat_sys_download_done)
            .setContentTitle("Download complete")
            .setContentText(filename)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).notify(notifId + 1, n)
    }

    private fun errorNotification(filename: String, reason: String) {
        val n = NotificationCompat.Builder(this, MADevApp.CHANNEL_DOWNLOADS)
            .setSmallIcon(android.R.drawable.stat_notify_error)
            .setContentTitle("Download failed")
            .setContentText("$filename: $reason")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).notify(notifId + 2, n)
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }

    companion object {
        const val EXTRA_URL      = "extra_url"
        const val EXTRA_FILENAME = "extra_filename"

        fun start(context: Context, url: String, filename: String) {
            val intent = Intent(context, DownloadService::class.java).apply {
                putExtra(EXTRA_URL, url)
                putExtra(EXTRA_FILENAME, filename)
            }
            context.startForegroundService(intent)
        }
    }
}
