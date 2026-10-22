package com.example.systemdesign.design.instagramFeed.di

import com.example.systemdesign.design.instagramFeed.data.remote.ProductApiService
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
    @Named("FeedRetrofit")
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl("https://dummyjson.com/").addConverterFactory(
            GsonConverterFactory.create()
        ).build()

    }


    @Provides
    @Singleton
    fun provideApiService(@Named("FeedRetrofit") retrofit: Retrofit): ProductApiService{
       return retrofit.create(ProductApiService::class.java)
    }

}