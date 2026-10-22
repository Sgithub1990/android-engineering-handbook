package com.example.systemdesign.design.offlineFirstStructure.di

import android.content.Context
import androidx.room.Room
import com.example.systemdesign.design.offlineFirstStructure.data.local.UserDao
import com.example.systemdesign.design.offlineFirstStructure.data.local.UserDb
import com.example.systemdesign.misllenious.DbMigration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase( @ApplicationContext context: Context): UserDb {
       return Room.databaseBuilder(context=context, UserDb::class.java, name = "userdb")
           .addMigrations(DbMigration.migration).build()
    }

    @Provides
    @Singleton
    fun provideDao(userDb: UserDb): UserDao {
        return  userDb.userDao()
    }

}