package com.example.systemdesign.android.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [StudentEntity::class], version = 1)
abstract class StudentDataBase: RoomDatabase() {
   abstract fun studentDao(): StudentDao

}