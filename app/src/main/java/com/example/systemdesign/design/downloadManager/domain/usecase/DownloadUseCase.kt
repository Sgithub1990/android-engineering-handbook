package com.example.systemdesign.design.downloadManager.domain.usecase

import javax.inject.Inject

data class DownloadUseCase @Inject constructor(
    val pauseUseCase: DownloadPauseUseCase,
    val cancelUseCase: DownloadCancelUseCase,
    val resumeUseCase: DownloadResumeUseCase,
    val startDownloadUseCase: StartDownloadUseCase
)
