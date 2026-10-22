package com.example.systemdesign.design.imageLoadingLibrary.di

import com.example.systemdesign.design.imageLoadingLibrary.data.repository.ImageDownLoadRepositoryImp
import com.example.systemdesign.design.imageLoadingLibrary.domain.ImageDownloadRepository
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
    fun bind(repository: ImageDownLoadRepositoryImp): ImageDownloadRepository

}