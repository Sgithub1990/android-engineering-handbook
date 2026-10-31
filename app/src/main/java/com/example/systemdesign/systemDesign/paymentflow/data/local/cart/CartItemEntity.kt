package com.example.systemdesign.systemDesign.paymentflow.data.local.cart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
                      @PrimaryKey
                      val productId: Long,
                      val title: String,
                      val price: Double,
                      val thumbnail: String,
                      val rating: Double,
                      val stock: Int,
                      val quantity: Int
                   )