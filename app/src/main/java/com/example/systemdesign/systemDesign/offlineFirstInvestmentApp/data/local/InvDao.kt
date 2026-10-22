package com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface InvDao {

   @Upsert
   suspend  fun insertPortFolio(entity: PortfolioEntity)

    @Upsert
    suspend fun insertHoldings(entity: List<HoldingEntity>)

   @Query("SELECT * FROM holding WHERE userId=:userId")
   fun getAllHoldings(userId: Long): Flow<List<HoldingEntity>>

   @Query("SELECT * FROM portfolio")
   fun getPortfolio(): Flow<PortfolioEntity?>


    @Query("SELECT * FROM holding WHERE userId=:userId and holdingUUId=:uuid")
    suspend fun getHoldingByUID(userId: Long,uuid: String): HoldingEntity?


    /**control concurrency*/
    @Query("UPDATE holding SET investedAmount=:amount WHERE userId=:userId and  holdingUUId=:uuid")
    suspend fun updateHoldingAmount(amount: Long,userId: Long,uuid: String): Int

    @Query("UPDATE holding SET syncStatus=:status WHERE  holdingUUId=:uuid")
    suspend fun updateHoldingStatus(status: SyncStatus,uuid: String)

    @Query("SELECT * FROM holding WHERE userId=:userId AND syncStatus = 'PENDING' ")
    suspend fun getPendingHolding(userId: Long): List<HoldingEntity>


    @Transaction
    suspend fun updateAmountAndMarkPending(
        userId: Long,
        uuid: String,
        amount: Long
    ): Int {
        val rowsUpdated =
            updateHoldingAmount(amount, userId, uuid)

        if (rowsUpdated > 0) {
            updateHoldingStatus(
                SyncStatus.PENDING,
                uuid
            )
        }
        return rowsUpdated
    }





    @Transaction
    suspend fun savePortfolioAndHolding(
        portfolio: PortfolioEntity,
        holdings: List<HoldingEntity>
    ){
        insertHoldings(holdings)
        insertPortFolio(portfolio)
   }

}