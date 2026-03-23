package com.madev.marketplace.data.repository

import com.madev.marketplace.data.local.dao.DownloadDao
import com.madev.marketplace.data.local.entity.DownloadEntity
import com.madev.marketplace.data.remote.StorageApi
import kotlinx.coroutines.flow.Flow

class DownloadRepository(
    private val downloadDao: DownloadDao,
    private val storageApi: StorageApi
) {
    fun getActiveDownloads(): Flow<List<DownloadEntity>> = downloadDao.getActiveDownloads()
    fun getAllDownloads(): Flow<List<DownloadEntity>> = downloadDao.getAllDownloads()

    suspend fun getSignedUrl(productId: String): String {
        return storageApi.getSignedDownloadUrl(productId)
    }

    suspend fun createDownload(download: DownloadEntity) {
        downloadDao.insert(download)
    }

    suspend fun updateProgress(id: String, bytes: Long, status: String) {
        downloadDao.updateProgress(id, bytes, status)
    }

    suspend fun updateStatus(id: String, status: String) {
        downloadDao.updateStatus(id, status)
    }

    suspend fun getByProductId(productId: String): DownloadEntity? {
        return downloadDao.getByProductId(productId)
    }

    suspend fun deleteDownload(id: String) {
        downloadDao.deleteById(id)
    }
}
