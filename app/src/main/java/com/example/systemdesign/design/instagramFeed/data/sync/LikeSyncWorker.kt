package com.example.systemdesign.design.instagramFeed.data.sync

import android.annotation.SuppressLint
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.systemdesign.design.instagramFeed.data.local.ProductDatabase
import com.example.systemdesign.design.instagramFeed.data.remote.ProductApiService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import okio.IOException


@HiltWorker
class LikeSyncWorker @AssistedInject constructor(@Assisted context: Context, @Assisted val parameters: WorkerParameters,
                                                 val apiService: ProductApiService, val productDatabase: ProductDatabase) : CoroutineWorker(context,parameters)
{
    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {
        /*update like in db*/
        val productId = parameters.inputData.getInt("productId",-1)
        productDatabase.productDao().updateLike(productId)
        productDatabase.productDao().updateLikeStatus(productId,2)
        /** handle request*/
       /* apiService.postLike()*/

       /*handel Response*/


       return  try {
           Result.Success()
       }catch (e: IOException){
           Result.Retry()
        }catch (e: Exception){
           Result.failure()
        }

    }
}