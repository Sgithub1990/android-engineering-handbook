package com.example.systemdesign.systemDesign.paymentflow.data.local.cart

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = REPLACE)
   suspend fun insertCart(cart: CartItemEntity)

    @Query("select * from cart_items")
    fun getCartList(): Flow<List<CartItemEntity>>


    @Query("select * from cart_items")
    fun getOrderCart(): List<CartItemEntity>


    @Query("update cart_items SET quantity=quantity+1 where productId=:productId and quantity<stock")
    fun increaseQuantity(productId: Long)

    @Query("update cart_items SET quantity=quantity-1 where productId=:productId and quantity>1")
    fun decreaseQuantity(productId: Long)

    @Query("delete from cart_items where productId=:productId")
    fun deleteCart(productId: Long)
}