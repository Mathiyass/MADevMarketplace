package com.madev.marketplace.data.repository

import com.madev.marketplace.data.local.dao.OrderDao
import com.madev.marketplace.data.local.entity.OrderEntity
import com.madev.marketplace.data.remote.OrderApi
import com.madev.marketplace.domain.model.Order
import com.madev.marketplace.domain.model.OrderItem
import kotlinx.coroutines.flow.Flow

class OrderRepository(
    private val orderApi: OrderApi,
    private val orderDao: OrderDao
) {
    fun getCachedOrders(): Flow<List<OrderEntity>> = orderDao.getAllOrders()

    suspend fun refreshOrders(userId: String) {
        val orders = orderApi.getUserOrders(userId)
        orderDao.insertAll(orders.map { it.toEntity() })
    }

    suspend fun createOrder(userId: String, totalAmount: Double): Order {
        return orderApi.createOrder(userId, totalAmount)
    }

    suspend fun createOrderItem(orderId: String, productId: String, pricePaid: Double): OrderItem {
        return orderApi.createOrderItem(orderId, productId, pricePaid)
    }

    suspend fun getUserPurchasedItems(userId: String): List<OrderItem> {
        return orderApi.getUserPurchasedItems(userId)
    }

    suspend fun getOrderById(orderId: String): Order {
        return orderApi.getOrderById(orderId)
    }

    suspend fun getOrderItems(orderId: String): List<OrderItem> {
        return orderApi.getOrderItems(orderId)
    }

    private fun Order.toEntity() = OrderEntity(
        orderId = orderId,
        userId = userId,
        totalAmount = totalAmount,
        paymentStatus = paymentStatus,
        paymentRef = paymentRef,
        orderDate = orderDate
    )
}
