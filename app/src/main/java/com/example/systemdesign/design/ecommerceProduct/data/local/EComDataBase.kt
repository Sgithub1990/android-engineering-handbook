package com.example.systemdesign.design.ecommerceProduct.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EComProductEntity::class, ERemoteKey::class, CartEntity::class], version = 101)
abstract class EComDataBase : RoomDatabase(){

   abstract fun eComDao(): EComProductDao
}