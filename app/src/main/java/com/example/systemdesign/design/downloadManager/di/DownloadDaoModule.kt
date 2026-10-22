package com.example.systemdesign.design.downloadManager.di

import android.content.Context
import androidx.room.Room
import com.example.systemdesign.design.downloadManager.data.local.DownLoadDataBase
import com.example.systemdesign.design.downloadManager.data.local.DownloadDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DownloadDaoModule {

    @Provides
    @Singleton
    fun provideDownLoadDataBase(@ApplicationContext context: Context): DownLoadDataBase {
        return Room.databaseBuilder(
            context,
            DownLoadDataBase::class.java,
            "downloadDb"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(downLoadDataBase: DownLoadDataBase): DownloadDao {
        return downLoadDataBase.downloadDao()
    }
}