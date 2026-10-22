package com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.worker
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local.InvDao
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local.SyncStatus
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.remote.Api
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CancellationException
import okio.IOException
import retrofit2.HttpException

@HiltWorker
class SyncPortFolioWorker @AssistedInject constructor(@Assisted context: Context, @Assisted val  workerParameters: WorkerParameters,val api: Api,val dao: InvDao):
    CoroutineWorker(context,workerParameters) {

    override suspend fun doWork(): Result {
       val userId = workerParameters.inputData.getLong("userId",0)
        if(userId<=0){
           return Result.failure()
        }
        return  try {
            val pendingHolding = dao.getPendingHolding(userId)
            for(holding in pendingHolding){
              val response =   api.updateHolding(holding.holdingUUId,"1000")
                if(response==true){
                  dao.updateHoldingStatus(SyncStatus.SUCCESS,holding.holdingUUId)
                }else{
                    Result.retry()
                }
            }

           val result =  api.userPortfolio(userId)
            if(result!=null && result.portfolio!=null && result.holdings!=null){
            dao.savePortfolioAndHolding(result.portfolio,result.holdings)
             Result.success()
            }else{
                Result.failure()
            }
          }catch (e: CancellationException){
            throw  e
        }catch (ex: IOException){
            Result.retry()
        }catch (e: HttpException){
            if(e.code() in 500..599){
                Result.retry()
            }else{
                Result.failure()
            }
        }
    }

    }