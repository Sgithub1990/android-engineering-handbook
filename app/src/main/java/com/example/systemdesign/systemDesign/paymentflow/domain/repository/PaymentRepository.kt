package com.example.systemdesign.systemDesign.paymentflow.domain.repository

import com.example.systemdesign.systemDesign.paymentflow.data.local.ApiState
import com.example.systemdesign.systemDesign.paymentflow.data.remote.OrderRequestData
import com.example.systemdesign.systemDesign.paymentflow.data.remote.dto.OrderResponse
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {

    suspend fun createOrder(requestData: List<OrderRequestData>): OrderResponse

    suspend fun insertOrder(orderResponse: OrderResponse)
}