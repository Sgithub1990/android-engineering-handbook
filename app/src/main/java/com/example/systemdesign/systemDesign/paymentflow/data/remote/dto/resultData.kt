package com.example.systemdesign.systemDesign.paymentflow.data.remote.dto

data class OrderResponse(
    val orderId: String,
    val userId: String,
    val totalAmount: Double,
    val currency: String,
    val status: String,
    val createdAt: String
)