package com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.di

import android.content.Context
import androidx.room.Room
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local.InvDao
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local.InvDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): InvDataBase{
       return Room.databaseBuilder(context, InvDataBase::class.java,"inv_database").build()
    }

    @Provides
    @Singleton
    fun provideDao(dataBase: InvDataBase): InvDao{
        return  dataBase.invDao()

    }
}