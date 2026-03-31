package com.madev.marketplace.data.local

import android.content.Context
import androidx.room.*
import com.madev.marketplace.domain.model.*

@Entity(tableName = "local_products")
data class LocalProduct(
    @PrimaryKey val id: String,
    val title: String,
    val price: Double,
    val category: String,
    val isPurchased: Boolean = false
)

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey val productId: String,
    val quantity: Int = 1
)

@Dao
interface ProductDao {
    @Query("SELECT * FROM local_products")
    suspend fun getAll(): List<LocalProduct>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(products: List<LocalProduct>)
}

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items")
    suspend fun getCartItems(): List<CartItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(item: CartItem)

    @Query("DELETE FROM cart_items WHERE productId = :id")
    suspend fun removeFromCart(id: String)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}

@Database(entities = [LocalProduct::class, CartItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "madev_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
