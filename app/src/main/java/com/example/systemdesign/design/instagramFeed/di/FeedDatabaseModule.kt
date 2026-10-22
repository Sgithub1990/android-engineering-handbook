package com.example.systemdesign.design.instagramFeed.di

import android.content.Context
import androidx.room.Room
import com.example.systemdesign.design.instagramFeed.data.local.ProductDao
import com.example.systemdesign.design.instagramFeed.data.local.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FeedDatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): ProductDatabase {
      return  Room.databaseBuilder(context,ProductDatabase::class.java,"product_db").build()
    }

    @Provides
    @Singleton
    fun provideDao(database: ProductDatabase): ProductDao{
      return  database.productDao()
    }
}