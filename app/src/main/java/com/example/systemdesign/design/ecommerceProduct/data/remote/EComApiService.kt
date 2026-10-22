package com.example.systemdesign.design.ecommerceProduct.data.remote

import com.example.systemdesign.design.ecommerceProduct.data.model.EComProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EComApiService {

    @GET("products")
   suspend fun getAllProductFromRemote(
        @Query("limit")limit: Int,
        @Query("skip")skip: Int
    ): EComProductResponse

   @GET("products/search")
    suspend fun searchApi(
        @Query("q")searchText: String
    ):EComProductResponse


}