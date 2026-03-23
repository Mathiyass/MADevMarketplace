package com.madev.marketplace.domain.usecase

import com.madev.marketplace.data.repository.OrderRepository
import com.madev.marketplace.domain.model.OrderItem

class GetLibraryUseCase(private val orderRepository: OrderRepository) {
    suspend operator fun invoke(userId: String): List<OrderItem> {
        return orderRepository.getUserPurchasedItems(userId)
    }
}
