package com.example.systemdesign.design.notificationSystem.di

import com.example.systemdesign.design.notificationSystem.data.remote.NotificationApi
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
object NetworkModule {


    @Provides
    @Singleton
    @Named("NotificationRetrofit")
    fun provideRetroFit(): Retrofit{
        return Retrofit.Builder().baseUrl("https://dummy-notification.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNotificationApi(@Named("NotificationRetrofit") retrofit: Retrofit): NotificationApi{
      return retrofit.create(NotificationApi::class.java)
    }

}
