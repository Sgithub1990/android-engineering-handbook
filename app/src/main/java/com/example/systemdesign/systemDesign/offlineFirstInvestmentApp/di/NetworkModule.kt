package com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.di

import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.remote.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit{
    return  Retrofit.Builder().baseUrl("https//:www.api.com")
          .addConverterFactory(GsonConverterFactory.create()).build()
  }

    @Provides
    @Singleton
    fun api(retrofit: Retrofit): Api{
       return retrofit.create(Api::class.java)
    }


}