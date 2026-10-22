package com.example.systemdesign.design.notificationSystem.di

import android.content.Context
import androidx.room.Room
import com.example.systemdesign.design.notificationSystem.data.local.TokenDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NotificationDatabaseModule {

    @Singleton
   @Provides
  fun provideTokenDatabase(@ApplicationContext context: Context): TokenDataBase{
      return Room.databaseBuilder(context=context, klass = TokenDataBase::class.java,"token_db").build()
  }

}