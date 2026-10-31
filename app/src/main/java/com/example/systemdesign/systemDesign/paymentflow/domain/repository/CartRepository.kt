package com.example.systemdesign.systemDesign.paymentflow.domain.repository

import com.example.systemdesign.systemDesign.paymentflow.data.local.cart.CartItemEntity
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    suspend fun insertCart(cart: CartItemEntity)

     fun getCart(): Flow<List<CartItemEntity>>

    suspend fun increaseCart(productId: Long)

    suspend fun decreaseCart(productId: Long)

    suspend fun deleteCart(productId: Long)




}