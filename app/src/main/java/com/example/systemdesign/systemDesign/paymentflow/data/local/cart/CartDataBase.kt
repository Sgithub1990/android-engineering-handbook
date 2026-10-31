package com.example.systemdesign.systemDesign.paymentflow.data.local.cart

import androidx.room.Database

@Database(entities = [CartItemEntity::class], version = 99)
abstract class CartDataBase {
    abstract fun cartDao(): CartDao
}