package com.example.systemdesign.design.offlineFirstStructure.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.systemdesign.design.offlineFirstStructure.repository.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.io.IOException

@HiltWorker
class UserSync @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val repository: UserRepository
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        return try {
            val result = repository.getUserFromRemote()
            repository.insertUser(result)
            Result.success()

        }catch (e: IOException) {
            /** Temporary network problem*/
            Result.retry()
        } catch (e: Exception) {
            /**   Permanent/unexpected failure*/
            Result.failure()
        }
    }
}