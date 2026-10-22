package com.example.systemdesign.design.notificationSystem.di

import com.example.systemdesign.design.notificationSystem.data.repository.NotificationRepoImp
import com.example.systemdesign.design.notificationSystem.domain.NotificationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NotificationRepositoryModule {

    @Binds
    @Singleton
   abstract fun provideRepository(notificationRepoImp: NotificationRepoImp): NotificationRepository
}