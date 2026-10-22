package com.example.systemdesign.design.chatApplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MessageEntity::class], version = 1)
@TypeConverters(ChatConverters::class)
abstract class ChatDataBase : RoomDatabase() {
    abstract fun msgDao(): MessageDao
}
