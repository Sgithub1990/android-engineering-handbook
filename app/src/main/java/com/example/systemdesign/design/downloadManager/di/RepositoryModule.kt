package com.example.systemdesign.design.downloadManager.di

import com.example.systemdesign.design.downloadManager.data.repository.DownloadRepositoryImp
import com.example.systemdesign.design.downloadManager.domain.repository.DownloadRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(repositoryImp: DownloadRepositoryImp): DownloadRepository

}