package com.example.systemdesign.design.chatApplication.data.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.systemdesign.design.chatApplication.domain.repository.ChatRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class MessageWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val repository: ChatRepository
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        /*get all pending msg*/
        return try {
            val failedMsg = repository.getAllFailedMsg()
            failedMsg.forEach {
                repository.resendMsg(it)
            }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
