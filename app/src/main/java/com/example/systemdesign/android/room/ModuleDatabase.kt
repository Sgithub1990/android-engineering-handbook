package com.example.systemdesign.android.room

import android.content.Context
import androidx.room.Room
import com.example.systemdesign.android.room.sqlcipher.KeyStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleDatabase {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): StudentDataBase{
       return Room.databaseBuilder(context, StudentDataBase::class.java,"studentDatabase")
            .addMigrations(DbMigration.migration)
           .setDriver(KeyStoreManager.driver)
           .openHelperFactory(KeyStoreManager.factory)
           .build()
    }

    @Provides
    @Singleton
    fun provideDao(database: StudentDataBase): StudentDao{
      return database.studentDao();
    }


}