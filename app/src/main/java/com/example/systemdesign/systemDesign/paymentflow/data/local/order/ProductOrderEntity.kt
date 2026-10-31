package com.example.systemdesign.systemDesign.paymentflow.data.local.order

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
data class ProductOrderEntity(
    @PrimaryKey
    val orderId: Long,
    val productId: Long,
    val paymentId: Long,
    val amount: Long,
    val status:OrderStatus
)
