package com.madev.marketplace.domain.model

data class DownloadTask(
    val id: String,
    val productId: String,
    val fileName: String,
    val url: String,
    val savedPath: String? = null,
    val bytesDownloaded: Long = 0,
    val totalBytes: Long = 0,
    val status: String = "queued"
) {
    val progressPercent: Int
        get() = if (totalBytes > 0) ((bytesDownloaded * 100) / totalBytes).toInt() else 0

    val formattedProgress: String
        get() {
            val downloaded = formatBytes(bytesDownloaded)
            val total = formatBytes(totalBytes)
            return "$downloaded / $total  $progressPercent%"
        }

    private fun formatBytes(bytes: Long): String {
        return when {
            bytes >= 1_073_741_824 -> String.format("%.1f GB", bytes / 1_073_741_824.0)
            bytes >= 1_048_576 -> String.format("%.1f MB", bytes / 1_048_576.0)
            bytes >= 1024 -> String.format("%.1f KB", bytes / 1024.0)
            else -> "$bytes B"
        }
    }
}
