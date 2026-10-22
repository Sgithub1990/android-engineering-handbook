package com.example.systemdesign.design.instagramFeed.domain

import androidx.paging.PagingData
import com.example.systemdesign.design.instagramFeed.data.local.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

     fun getPagingData(): Flow<PagingData<ProductEntity>>

     fun syncLike(productId: Int)


}