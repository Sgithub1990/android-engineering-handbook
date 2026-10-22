package com.example.systemdesign.design.ecommerceProduct.data.model

import com.example.systemdesign.design.ecommerceProduct.data.local.EComProductEntity

data class EComProductResponse(
    val products: List<EComProductEntity>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
