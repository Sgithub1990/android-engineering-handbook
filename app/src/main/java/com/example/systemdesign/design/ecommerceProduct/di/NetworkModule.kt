package com.example.systemdesign.design.ecommerceProduct.di

import com.example.systemdesign.design.ecommerceProduct.data.remote.EComApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    @Named("EComRetrofit")
    fun provideRetrofit(): Retrofit{
      return  Retrofit.Builder().baseUrl("https://dummyjson.com/").addConverterFactory(
            GsonConverterFactory.create()
        ).build()
    }


    @Provides
    @Singleton
    fun provideEComApiService(@Named("EComRetrofit") retrofit: Retrofit): EComApiService{
       return retrofit.create(EComApiService::class.java)
    }

}