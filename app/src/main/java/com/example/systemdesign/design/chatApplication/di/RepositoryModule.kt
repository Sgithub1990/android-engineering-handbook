package com.example.systemdesign.design.chatApplication.di

import com.example.systemdesign.design.chatApplication.data.repository.ChatRepositoryImp
import com.example.systemdesign.design.chatApplication.domain.repository.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ChatRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(imp: ChatRepositoryImp): ChatRepository

}
