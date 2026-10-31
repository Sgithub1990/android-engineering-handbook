package com.example.systemdesign.systemDesign.paymentflow.data.remote

import com.example.systemdesign.systemDesign.paymentflow.data.local.product.ProductEntity
import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    fun getProduct(): List<ProductEntity>
}