package com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portfolio")
data class PortfolioEntity(
    @PrimaryKey
    val userID: Long,
    val investedAmount: Long,
    val currentValue: Long,
    val lastSyncTime: Long,
    val syncStatus:SyncStatus
)
