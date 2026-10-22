package com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.domain.repository

import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local.HoldingEntity
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local.SyncStatus
import kotlinx.coroutines.flow.Flow

interface InvRepository {

    fun getAllHolding(userId: Long): Flow<List<HoldingEntity>>

   suspend fun refreshHolding(userId: Long)
   suspend fun updateAmount(userId: Long,uuid: String,amount: Long)

    suspend fun enQueueWork(userId: Long)

    suspend fun syncStatus():Flow<SyncStatus>?
}