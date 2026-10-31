package com.example.systemdesign.epam.testCase.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 101)
abstract class MovieDatabase : RoomDatabase() {
   abstract fun movieDao(): MovieDao
}