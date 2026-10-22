package com.example.systemdesign.design.notificationSystem.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.systemdesign.design.notificationSystem.domain.NotificationRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


@HiltWorker
class TokenSyncWorker @AssistedInject constructor(val notificationRepository: NotificationRepository, @Assisted appContext: Context,  @Assisted params: WorkerParameters):
    CoroutineWorker(appContext,params) {
    override suspend fun doWork(): Result {
       try {
          notificationRepository.uploadToken()
           return Result.success()
       }catch (e: Exception){
           return Result.retry()
       }
    }
}