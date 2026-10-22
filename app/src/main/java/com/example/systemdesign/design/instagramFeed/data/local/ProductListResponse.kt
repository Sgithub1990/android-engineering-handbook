package com.example.systemdesign.design.instagramFeed.data.local


data class ProductListResponse (val products: List<ProductEntity>,
                                val total: Int,
                                val skip: Int,
                                val limit: Int)