package com.example.systemdesign.systemDesign.paymentflow.domain.repository

import com.example.systemdesign.systemDesign.paymentflow.data.local.ApiState
import com.example.systemdesign.systemDesign.paymentflow.data.local.order.ProductOrderEntity
import com.example.systemdesign.systemDesign.paymentflow.data.local.product.ProductEntity
import com.example.systemdesign.systemDesign.paymentflow.data.remote.OrderRequestData
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts(): Flow<ApiState<List<ProductEntity>>>

}