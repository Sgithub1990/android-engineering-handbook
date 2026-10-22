package com.example.systemdesign.design.instagramFeed.data.remote

import com.example.systemdesign.design.instagramFeed.data.local.ProductEntity
import com.example.systemdesign.design.instagramFeed.data.local.ProductListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiService {

    @GET("products")
   suspend fun getProduct(
        @Query("limit")limit:Int,
        @Query("skip") skip: Int
    ): ProductListResponse
}