package com.example.systemdesign.epam.mvvm.di

import com.example.systemdesign.epam.mvvm.data.remote.EUserApi
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
       return Retrofit.Builder().baseUrl("https://dummyjson.com").addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun userApi(retrofit: Retrofit): EUserApi{
        return  retrofit.create(EUserApi::class.java)
    }


}