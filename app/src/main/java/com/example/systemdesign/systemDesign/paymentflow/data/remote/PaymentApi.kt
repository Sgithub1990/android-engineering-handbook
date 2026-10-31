package com.example.systemdesign.systemDesign.paymentflow.data.remote

import com.example.systemdesign.systemDesign.paymentflow.data.local.order.ProductOrderEntity
import com.example.systemdesign.systemDesign.paymentflow.data.remote.dto.OrderResponse
import retrofit2.http.POST

interface PaymentApi {

   @POST("payment/create-order")
    fun  creteOrder(data: List<OrderRequestData>): OrderResponse




}