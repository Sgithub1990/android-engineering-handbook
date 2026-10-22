package com.example.systemdesign.design.downloadManager.di

import com.example.systemdesign.design.downloadManager.data.remote.DownloadApi
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
    @Named("downLoadFile")
    fun provideRetrofit(): Retrofit{
      return  Retrofit.Builder()
          .baseUrl("https://dummyjson.com/")
          .addConverterFactory(GsonConverterFactory.create())
          .build()
    }

    @Provides
    @Singleton
    fun provideDownloadApi(@Named("downLoadFile") retrofit: Retrofit): DownloadApi{
       return retrofit.create(DownloadApi::class.java)
    }

}