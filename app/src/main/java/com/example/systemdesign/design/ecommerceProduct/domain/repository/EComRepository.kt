package com.example.systemdesign.design.ecommerceProduct.domain.repository

import androidx.paging.PagingData
import com.example.systemdesign.design.ecommerceProduct.data.local.CartEntity
import com.example.systemdesign.design.ecommerceProduct.data.local.EComProductEntity
import kotlinx.coroutines.flow.Flow

interface EComRepository {



     fun getCartList(): Flow<List<CartEntity>>

     fun getPager(): Flow<PagingData<EComProductEntity>>

    suspend fun removeCartItem(id: Int)

    suspend fun insertIntoCart(product: EComProductEntity?)

    suspend fun searchApi(searchText: String)


}