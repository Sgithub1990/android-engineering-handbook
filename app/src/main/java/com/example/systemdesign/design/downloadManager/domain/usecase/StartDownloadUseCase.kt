package com.example.systemdesign.design.downloadManager.domain.usecase

import com.example.systemdesign.design.downloadManager.domain.repository.DownloadRepository
import javax.inject.Inject

class StartDownloadUseCase @Inject constructor(private val repository: DownloadRepository) {

    suspend fun invoke(url: String,fileName: String){
        repository.startDownload(url,fileName)
    }
}