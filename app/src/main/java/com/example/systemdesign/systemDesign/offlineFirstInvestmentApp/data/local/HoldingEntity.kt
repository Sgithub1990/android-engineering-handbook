package com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "holding")
data class HoldingEntity(
    @PrimaryKey
    val holdingUUId: String,
    val userId: Long,
    val fundName: String,
    val investedAmount: Long,
    val currentValue: Long,
    val syncStatus:SyncStatus
   )
