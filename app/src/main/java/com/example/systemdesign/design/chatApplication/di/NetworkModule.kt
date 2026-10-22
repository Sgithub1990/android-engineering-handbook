package com.example.systemdesign.design.chatApplication.di

import com.example.systemdesign.design.chatApplication.data.auth.AuthInterCeptor
import com.example.systemdesign.design.chatApplication.data.auth.TokenManager
import com.example.systemdesign.design.chatApplication.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @Named("ChatHttpClient")
    fun provideHttpClient(tokenManager: TokenManager): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterCeptor(tokenManager))
             .build()
    }


    @Provides
    @Singleton
    @Named("ChatRetrofit")
    fun provideRetroFit(@Named("ChatHttpClient") okHttpClient: OkHttpClient): Retrofit{
       return Retrofit.Builder().baseUrl("https://dummy-chat.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(@Named("ChatRetrofit") retrofit: Retrofit): ApiService{
       return retrofit.create(ApiService::class.java)
    }

}
