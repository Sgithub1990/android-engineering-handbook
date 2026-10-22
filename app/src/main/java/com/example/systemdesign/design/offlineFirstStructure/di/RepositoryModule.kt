package com.example.systemdesign.design.offlineFirstStructure.di

import com.example.systemdesign.design.offlineFirstStructure.data.repository.UserRepositoryImp
import com.example.systemdesign.design.offlineFirstStructure.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface  RepositoryModule {
    @Binds
    @Singleton
    fun bindUserRepository(userRepositoryImp: UserRepositoryImp): UserRepository
}