package com.example.systemdesign.design.chatApplication.data.sync

import androidx.work.BackoffPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ScheduleWorker @Inject constructor(val workManager: WorkManager) {

    fun enqueueMessage(){
        val request = OneTimeWorkRequest.Builder(MessageWorker::class.java)
           .setConstraints(androidx.work.Constraints.Builder()
             .setRequiredNetworkType(NetworkType.CONNECTED)
             .setRequiresBatteryNotLow(true)
             .build()
           )
           .setBackoffCriteria(BackoffPolicy.EXPONENTIAL,3, TimeUnit.MINUTES)
              .build()

          workManager.enqueueUniqueWork("SYNC_MSG", ExistingWorkPolicy.KEEP,request)




    }
}