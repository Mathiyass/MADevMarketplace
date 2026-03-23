package com.madev.marketplace.domain.usecase

import com.madev.marketplace.data.repository.CartRepository
import com.madev.marketplace.data.repository.OrderRepository
import com.madev.marketplace.domain.model.Order

class PlaceOrderUseCase(
    private val orderRepository: OrderRepository,
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(userId: String, items: List<Pair<String, Double>>): Order {
        val total = items.sumOf { it.second }
        val order = orderRepository.createOrder(userId, total)
        items.forEach { (productId, price) ->
            orderRepository.createOrderItem(order.orderId, productId, price)
        }
        cartRepository.clearCart()
        return order
    }
}
