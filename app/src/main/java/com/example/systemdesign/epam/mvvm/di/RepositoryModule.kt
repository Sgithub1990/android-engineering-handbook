package com.example.systemdesign.epam.mvvm.di

import com.example.systemdesign.epam.mvvm.data.repository.EUserRepository
import com.example.systemdesign.epam.mvvm.data.repository.EUserRepositoryImp
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
    fun bind(repository: EUserRepositoryImp): EUserRepository
}