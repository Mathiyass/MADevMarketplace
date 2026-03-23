package com.madev.marketplace.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloads")
data class DownloadEntity(
    @PrimaryKey val id: String,
    val productId: String,
    val url: String,
    val savedPath: String?,
    val bytesDownloaded: Long = 0,
    val totalBytes: Long = 0,
    val status: String = "queued", // queued, downloading, paused, complete, error
    val fileName: String
)
