package com.example.systemdesign.systemDesign.paymentflow.data.repository

import com.example.systemdesign.systemDesign.paymentflow.data.local.ApiState
import com.example.systemdesign.systemDesign.paymentflow.data.local.order.PaymentDao
import com.example.systemdesign.systemDesign.paymentflow.data.local.order.ProductOrderEntity
import com.example.systemdesign.systemDesign.paymentflow.data.local.product.ProductDao
import com.example.systemdesign.systemDesign.paymentflow.data.remote.OrderRequestData
import com.example.systemdesign.systemDesign.paymentflow.data.remote.PaymentApi
import com.example.systemdesign.systemDesign.paymentflow.data.remote.ProductApi
import com.example.systemdesign.systemDesign.paymentflow.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(val productApi: ProductApi,  val productDao: ProductDao) : ProductRepository {
    override  fun getProducts(): Flow<ApiState<List<com.example.systemdesign.systemDesign.paymentflow.data.local.product.ProductEntity>>> = flow {
         emit(ApiState.Loading)
        try {
            val result = productApi.getProduct()
            productDao.insertProduct(result)

            emitAll(productDao.getAllProduct().map {
                ApiState.Success(it)
             })

        }catch (e: Exception){
         emit(ApiState.Error(e.message.toString()))
         }
    }




}