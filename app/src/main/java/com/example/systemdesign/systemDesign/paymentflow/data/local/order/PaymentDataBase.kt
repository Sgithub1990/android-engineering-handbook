package com.example.systemdesign.systemDesign.paymentflow.data.local.order

import androidx.room.Database

@Database(entities = [ProductOrderEntity::class], version = 101)
abstract class PaymentDataBase {

    abstract  fun paymentDao(): PaymentDao
}