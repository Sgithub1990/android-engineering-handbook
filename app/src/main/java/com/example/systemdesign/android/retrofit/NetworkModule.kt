package com.example.systemdesign.android.retrofit

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
      return  Retrofit.Builder().baseUrl("https://example.com/api/").addConverterFactory(GsonConverterFactory.create()).build()
     }


    @Provides
    @Singleton
    fun provideRequest(retrofit: Retrofit): StudentApi{
      return retrofit.create(StudentApi::class.java)
     }



}