package com.example.systemdesign.design.notificationSystem.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.systemdesign.design.notificationSystem.domain.Token

@Database(entities = [Token::class], version = 10)
abstract class TokenDataBase : RoomDatabase(){

    abstract fun getTokenDao(): TokenDao
}