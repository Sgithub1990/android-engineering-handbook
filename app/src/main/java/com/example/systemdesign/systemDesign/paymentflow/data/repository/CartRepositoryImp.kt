package com.example.systemdesign.systemDesign.paymentflow.data.repository

import com.example.systemdesign.systemDesign.paymentflow.data.local.cart.CartDao
import com.example.systemdesign.systemDesign.paymentflow.data.local.cart.CartItemEntity
import com.example.systemdesign.systemDesign.paymentflow.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImp @Inject constructor(val cartDao: CartDao): CartRepository {
    override suspend fun insertCart(cart: CartItemEntity) {
       cartDao.insertCart(cart)
    }

    override fun getCart(): Flow<List<CartItemEntity>> {
       return cartDao.getCartList()
    }

    override suspend fun increaseCart(productId: Long) {
       cartDao.increaseQuantity(productId)
    }

    override suspend fun decreaseCart(productId: Long) {
        cartDao.decreaseQuantity(productId)
    }

    override suspend fun deleteCart(productId: Long) {
       cartDao.deleteCart(productId)
    }


}