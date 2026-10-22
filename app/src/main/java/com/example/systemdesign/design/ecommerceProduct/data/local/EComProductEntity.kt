package com.example.systemdesign.design.ecommerceProduct.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eComProduct")
data class EComProductEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val thumbnail: String,
    val stock: Int,
    val discountPercentage: Double,
    val price: Double,
    val rating: Double

)



