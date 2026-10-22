package com.example.systemdesign.design.notificationSystem.worker

import android.content.Context
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

object SyncScheduler {

    fun enqueue(context: Context){
       val request = OneTimeWorkRequestBuilder<TokenSyncWorker>().setConstraints(
           androidx.work.Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
       ).build()

       WorkManager.getInstance(context).enqueue(request)
    }

}