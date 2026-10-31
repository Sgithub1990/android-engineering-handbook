package com.example.systemdesign.systemDesign.paymentflow.data.local.product

import androidx.room.Entity

@Entity(tableName = "product")
data class ProductEntity(
    val id: Long,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val rating: Double,
    val stock: Int
)