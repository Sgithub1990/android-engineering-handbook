package com.example.systemdesign.design.offlineFirstStructure.di

import com.example.systemdesign.design.offlineFirstStructure.data.remote.UserApiService
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
    @Named("UserRetrofit")
    fun provideRetrofit(): Retrofit{
      return  Retrofit.Builder().baseUrl("https://dummyjson.com/").addConverterFactory(
            GsonConverterFactory.create()).build()
    }


    @Provides
    @Singleton
    fun provideApiService(@Named("UserRetrofit") retrofit: Retrofit): UserApiService {
      return retrofit.create(UserApiService::class.java)
    }
}