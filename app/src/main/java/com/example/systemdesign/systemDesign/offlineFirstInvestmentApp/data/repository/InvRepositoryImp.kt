package com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.repository

import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local.HoldingEntity
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local.InvDao
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local.SyncStatus
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.domain.repository.InvRepository
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.worker.SyncPortFolioWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class InvRepositoryImp @Inject constructor(val dao: InvDao,val workManager: WorkManager) : InvRepository{
    override fun getAllHolding(userId: Long): Flow<List<HoldingEntity>> {
       return dao.getAllHoldings(userId)
    }

    override suspend fun refreshHolding(userId: Long) {
        enQueueWork(userId)
     }

    override  suspend fun updateAmount(userId: Long,uuid: String,amount: Long){
        require(amount >= 0) {
            "Investment amount cannot be negative"
        }
        val rowEffected = dao.updateAmountAndMarkPending(userId,uuid,amount)
        if(rowEffected>0){
            enQueueWork(userId)
        }
    }

    override suspend fun enQueueWork(userId: Long) {
        val workRequest = OneTimeWorkRequestBuilder<SyncPortFolioWorker>()
                        .setInputData(Data.Builder().putLong("userId",userId).build())
            .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
            .setBackoffCriteria(BackoffPolicy.EXPONENTIAL,10, TimeUnit.SECONDS)
            .build()

        workManager.enqueueUniqueWork("InvestmentWork", ExistingWorkPolicy.KEEP,workRequest)

    }

    override suspend fun syncStatus(): Flow<SyncStatus>? {
       val state =  workManager.getWorkInfosForUniqueWorkFlow("InvestmentWork")
       state.first().map {
           return when(it.state){
               WorkInfo.State.ENQUEUED,
               WorkInfo.State.RUNNING,
               WorkInfo.State.SUCCEEDED -> {
                   flowOf(SyncStatus.SUCCESS)
               }

               WorkInfo.State.FAILED -> {
                   flowOf(SyncStatus.FAILED)
               }

               else -> {
                   flowOf(SyncStatus.PENDING)
               }
           }

       }

     return null
    }
}