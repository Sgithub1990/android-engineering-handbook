package com.example.systemdesign.systemDesign.paymentflow.data.local.product

import androidx.room.Database

@Database(entities = [ProductEntity::class], version = 100)
abstract class ProductDataBase {
  abstract  fun productDao(): ProductDao
}