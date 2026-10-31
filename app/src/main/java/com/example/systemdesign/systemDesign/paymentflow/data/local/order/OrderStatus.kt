package com.example.systemdesign.systemDesign.paymentflow.data.local.order

enum class OrderStatus {
    CREATED,
    PAYMENT_PENDING,
    PAID,
    PAYMENT_FAILED,
    CANCELLED,
    REFUNDED
}