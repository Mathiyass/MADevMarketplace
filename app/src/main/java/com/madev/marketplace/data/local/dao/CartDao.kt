package com.madev.marketplace.data.local.dao

import androidx.room.*
import com.madev.marketplace.data.local.entity.CartItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items ORDER BY addedAt DESC")
    fun getAllItems(): Flow<List<CartItemEntity>>

    @Query("SELECT COUNT(*) FROM cart_items")
    fun getItemCount(): Flow<Int>

    @Query("SELECT SUM(price) FROM cart_items")
    fun getTotal(): Flow<Double?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CartItemEntity)

    @Delete
    suspend fun delete(item: CartItemEntity)

    @Query("DELETE FROM cart_items WHERE productId = :productId")
    suspend fun deleteByProductId(productId: String)

    @Query("DELETE FROM cart_items")
    suspend fun clearAll()

    @Query("SELECT EXISTS(SELECT 1 FROM cart_items WHERE productId = :productId)")
    suspend fun isInCart(productId: String): Boolean
}
