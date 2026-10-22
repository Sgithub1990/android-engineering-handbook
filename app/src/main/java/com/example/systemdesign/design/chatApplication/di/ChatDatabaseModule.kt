package com.example.systemdesign.design.chatApplication.di

import android.content.Context
import androidx.room.Room
import com.example.systemdesign.design.chatApplication.data.local.ChatDataBase
import com.example.systemdesign.design.chatApplication.data.local.MessageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatDatabaseModule {

    @Provides
    @Singleton
    fun provideChatDatabase(@ApplicationContext context: Context): ChatDataBase {
        return Room.databaseBuilder(
            context,
            ChatDataBase::class.java,
            "chat_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMessageDao(database: ChatDataBase): MessageDao {
        return database.msgDao()
    }
}
