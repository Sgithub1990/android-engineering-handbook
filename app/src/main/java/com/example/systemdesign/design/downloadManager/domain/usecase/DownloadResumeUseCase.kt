package com.example.systemdesign.design.downloadManager.domain.usecase

import com.example.systemdesign.design.downloadManager.domain.repository.DownloadRepository
import javax.inject.Inject

class DownloadResumeUseCase @Inject constructor(private val repository: DownloadRepository) {

    suspend fun invoke(id: String){
        repository.resumeDownLoad(id)
    }
}