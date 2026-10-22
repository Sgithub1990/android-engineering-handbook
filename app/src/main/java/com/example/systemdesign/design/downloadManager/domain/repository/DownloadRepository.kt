package com.example.systemdesign.design.downloadManager.domain.repository

import com.example.systemdesign.design.downloadManager.data.local.DownloadEntity
import kotlinx.coroutines.flow.Flow


interface DownloadRepository {

    fun observerDownload(): Flow<List<DownloadEntity>>

    suspend fun startDownload(url: String,fileName: String)


    suspend fun pauseDownLoad(id: String)

     suspend fun resumeDownLoad(id: String)

    suspend fun cancelDownLoad(id: String)




}