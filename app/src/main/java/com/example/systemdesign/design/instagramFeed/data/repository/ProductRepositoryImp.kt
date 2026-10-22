package com.example.systemdesign.design.instagramFeed.data.repository

import android.annotation.SuppressLint
import android.icu.util.TimeUnit
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.systemdesign.design.instagramFeed.data.local.ProductDatabase
import com.example.systemdesign.design.instagramFeed.data.local.ProductEntity
import com.example.systemdesign.design.instagramFeed.data.remote.ProductApiService
import com.example.systemdesign.design.instagramFeed.data.sync.LikeSyncWorker
import com.example.systemdesign.design.instagramFeed.domain.ProductRepository
import com.example.systemdesign.design.instagramFeed.paging.PRemoteMediator
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(val workManager: WorkManager,val database: ProductDatabase,val apiService: ProductApiService) : ProductRepository {
    @OptIn(ExperimentalPagingApi::class)
    override  fun getPagingData(): Flow<PagingData<ProductEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 10, maxSize = 100, prefetchDistance = 5),
            remoteMediator = PRemoteMediator(apiService = apiService, productDataBase = database),
            pagingSourceFactory = {
                database.productDao().getAllProduct()
            }
        ).flow
    }

    @SuppressLint("RestrictedApi")
    override fun syncLike(productId: Int) {
       val request = OneTimeWorkRequestBuilder<LikeSyncWorker>()
                    .setConstraints(
                        Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
                    )
           .setBackoffCriteria(BackoffPolicy.EXPONENTIAL,100, timeUnit = java.util.concurrent.TimeUnit.MILLISECONDS)
           .setInputData(Data.Builder().put("productId",productId).build())
           .build()
        workManager.enqueue(request)
    }
}