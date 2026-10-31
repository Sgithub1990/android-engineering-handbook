package com.example.systemdesign.systemDesign.paymentflow.data.repository

import com.example.systemdesign.systemDesign.paymentflow.data.local.order.PaymentDao
import com.example.systemdesign.systemDesign.paymentflow.data.remote.OrderRequestData
import com.example.systemdesign.systemDesign.paymentflow.data.remote.PaymentApi
import com.example.systemdesign.systemDesign.paymentflow.data.remote.dto.OrderResponse
import com.example.systemdesign.systemDesign.paymentflow.domain.repository.PaymentRepository

import javax.inject.Inject

class PaymentRepositoryImp @Inject constructor(val paymentApi: PaymentApi,val paymentDao: PaymentDao): PaymentRepository {
    override suspend fun createOrder(requestData: List<OrderRequestData>): OrderResponse{
      return  paymentApi.creteOrder(requestData)

    }

    override suspend fun insertOrder(orderResponse: OrderResponse) {
       paymentDao.insertOrder(orderResponse)
    }
}