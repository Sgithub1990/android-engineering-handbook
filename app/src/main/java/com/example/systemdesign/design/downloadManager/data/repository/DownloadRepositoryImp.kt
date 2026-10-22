package com.example.systemdesign.design.downloadManager.data.repository

import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.systemdesign.design.downloadManager.data.local.DownloadDao
import com.example.systemdesign.design.downloadManager.data.local.DownloadEntity
import com.example.systemdesign.design.downloadManager.data.local.DownloadStatus
import com.example.systemdesign.design.downloadManager.domain.repository.DownloadRepository
import com.example.systemdesign.design.downloadManager.syncworker.SyncDownloadWorker
import kotlinx.coroutines.flow.Flow
import java.io.File
import java.util.UUID
import javax.inject.Inject

class DownloadRepositoryImp @Inject constructor(private val dao: DownloadDao,private val workManager: WorkManager) : DownloadRepository{
    override fun observerDownload(): Flow<List<DownloadEntity>> {
      return dao.getAllDownLoad()
    }

    override  suspend fun startDownload(url: String,fileName: String) {
       val id =  UUID.randomUUID().toString()
       val downLoadEntity = DownloadEntity(
           id = id,
           url = url,
           fileName = fileName
       )
       dao.insert(downLoadEntity)

       val request = OneTimeWorkRequestBuilder<SyncDownloadWorker>()
           .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).setRequiresBatteryNotLow(true).build())
           .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 100, java.util.concurrent.TimeUnit.MILLISECONDS)
           .build()

        workManager.enqueueUniqueWork("download_$id", ExistingWorkPolicy.KEEP,request)
    }

    override suspend fun pauseDownLoad(id: String) {

        /**update Status*/
        dao.updateDownLoadStatus(id, DownloadStatus.PAUSE,"")

        /**cancel worker*/
        workManager.cancelUniqueWork("download_$id")

    }

    override suspend fun resumeDownLoad(id: String) {
        /** get existing download entity */
        val downloadEntity = dao.getDownLoadById(id);

        /** update status*/
        dao.updateDownLoadStatus(id, DownloadStatus.DOWNLOADING,"")

        /** create worker request*/

        val request =
            OneTimeWorkRequestBuilder<SyncDownloadWorker>()
                .setInputData(
                    workDataOf("DOWNLOAD_ID" to id)
                )
                .build()

        workManager.enqueueUniqueWork(
            "download_$id",
            ExistingWorkPolicy.REPLACE,
            request
        )


    }

    override suspend fun cancelDownLoad(id: String) {
        /** get partial download entity */
        val download =
            dao.getDownLoadById(id)
                ?: return
        /**update Status*/
        dao.updateDownLoadStatus(id, DownloadStatus.CANCEL,"")


        /**cancel worker*/
        workManager.cancelUniqueWork("download_$id")

        /** delete partial file*/

      /** Delete partial file*/
        val filePath = download.filePath?:""
        val file = File(filePath)
        if (file.exists()) {
            file.delete()
        }


    }

}