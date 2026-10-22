package com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import java.time.temporal.TemporalAmount
import java.util.UUID

interface Api {

    @GET("investment/portfolio")
    fun userPortfolio( @Query("userId")userId: Long):PortfolioResponse?



   @GET("investment/portfolio/sync")
   fun updateHolding( @Query("uid")uuid: String,@Query("amount")amount: String): Boolean?


}