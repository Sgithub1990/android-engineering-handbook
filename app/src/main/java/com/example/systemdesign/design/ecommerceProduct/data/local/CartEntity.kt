package com.example.systemdesign.design.ecommerceProduct.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
                      @PrimaryKey
                      val id: Int,
                      val title: String,
                      val description: String,
                      val category: String,
                      val thumbnail: String,
)