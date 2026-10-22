package com.example.systemdesign.design.instagramFeed.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class, RemoteKeyEntity::class], version = 2)
abstract class ProductDatabase: RoomDatabase() {
   abstract fun productDao(): ProductDao


}