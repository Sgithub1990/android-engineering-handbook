package com.example.systemdesign.design.instagramFeed.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.systemdesign.design.instagramFeed.data.local.ProductDatabase
import com.example.systemdesign.design.instagramFeed.data.local.ProductEntity
import com.example.systemdesign.design.instagramFeed.data.remote.ProductApiService
import com.example.systemdesign.revision.InstaGramFeed.InstaRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository  @Inject constructor(val database: ProductDatabase,val apiService: ProductApiService) {

    @OptIn(ExperimentalPagingApi::class)
    fun pager(): Flow<PagingData<ProductEntity>> {
      return  Pager(config =
            PagingConfig(pageSize = 20, prefetchDistance = 5, maxSize = 40),
            remoteMediator = InstaRemoteMediator(database,apiService),
            pagingSourceFactory = {
                database.productDao().getAllProduct()
            }).flow

    }
}