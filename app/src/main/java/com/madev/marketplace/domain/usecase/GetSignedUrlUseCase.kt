package com.madev.marketplace.domain.usecase

import com.madev.marketplace.data.repository.DownloadRepository

class GetSignedUrlUseCase(private val downloadRepository: DownloadRepository) {
    suspend operator fun invoke(productId: String): String {
        return downloadRepository.getSignedUrl(productId)
    }
}
