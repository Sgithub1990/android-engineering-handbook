package com.example.systemdesign.design.ecommerceProduct.di

import android.content.Context
import androidx.room.Room
import com.example.systemdesign.design.ecommerceProduct.data.local.EComDataBase
import com.example.systemdesign.design.ecommerceProduct.data.local.EComProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EComDatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase( @ApplicationContext context: Context): EComDataBase{
        return Room.databaseBuilder(context, EComDataBase::class.java,"ecomdb").build()
    }

    @Provides
    @Singleton
    fun provideDao(dataBase: EComDataBase): EComProductDao
    {
        return dataBase.eComDao()
    }
}