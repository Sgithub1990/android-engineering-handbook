package com.example.systemdesign.design.instagramFeed.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productEntity")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val minimumOrderQuantity: Int,
    val isLiked: Boolean = false,
    val likeSyncStatus: Int

)
