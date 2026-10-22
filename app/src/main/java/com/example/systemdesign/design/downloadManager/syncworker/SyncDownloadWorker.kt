package com.example.systemdesign.design.downloadManager.syncworker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.systemdesign.design.downloadManager.data.local.DownloadDao
import com.example.systemdesign.design.downloadManager.data.local.DownloadStatus
import com.example.systemdesign.design.downloadManager.data.remote.DownloadClient
import com.example.systemdesign.design.downloadManager.notification.NotificationUtils
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import okio.IOException
import java.io.File

@HiltWorker
class SyncDownloadWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val workerParameters: WorkerParameters,
    val downloadClient: DownloadClient,
    val dao: DownloadDao
): CoroutineWorker(context,workerParameters) {
    override suspend fun doWork(): Result {

        /**get downloadEntity from database*/
        val downloadId =
            inputData.getString("DOWNLOAD_ID")
        val downLoadEntity = dao.getDownLoadById(downloadId!!) ?: return Result.failure()

       /** create output file */
        val outputFile = File(
          applicationContext.filesDir,
          downLoadEntity.fileName!!
       )
        try {

          /**   Mark as downloading*/
            dao.updateDownLoadStatus(
                id = downloadId,
                status = DownloadStatus.DOWNLOADING,
                error = ""
            )
            /** start  notification */
               setForeground(NotificationUtils.createForeGroundInfo(context,downLoadEntity.fileName,0))
                downloadClient.downLoadFile(downLoadEntity,outputFile,{
                setForeground(NotificationUtils.createForeGroundInfo(context,downLoadEntity.fileName,it))
                })

            /** Download completed*/
            dao.updateDownLoadStatus(
                id = downloadId,
                status = DownloadStatus.COMPLETED,
                error = ""
            )
            return Result.success()
         }catch (e: IOException){
            dao.updateDownLoadStatus(
                id = downloadId,
                status = DownloadStatus.FAILED,
                error = e.message.toString()
            )
           return Result.retry()
        }catch (e: Exception){
            dao.updateDownLoadStatus(
                id = downloadId,
                status = DownloadStatus.FAILED,
                error = e.message.toString()
            )

          return  Result.failure()
        }

    }
}