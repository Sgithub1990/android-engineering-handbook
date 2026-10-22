package com.example.systemdesign.design.downloadManager.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.systemdesign.design.downloadManager.domain.repository.DownloadRepository
import com.example.systemdesign.design.downloadManager.domain.usecase.DownloadUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(val useCase: DownloadUseCase,val repository: DownloadRepository): ViewModel() {



    val allDownload = repository.observerDownload().stateIn(viewModelScope,
        started = SharingStarted.WhileSubscribed(),
            emptyList())


    fun startDownload(url: String,fileName: String){
        viewModelScope.launch {
        useCase.startDownloadUseCase.invoke(url,fileName)
        }
    }
    fun pause(id: String){
        viewModelScope.launch {
            useCase.pauseUseCase.invoke(id)
        }
    }

    fun cancel(id: String){
        viewModelScope.launch {
            useCase.cancelUseCase.invoke(id)
        }
    }


    fun resume(id: String){
        viewModelScope.launch {
            useCase.resumeUseCase.invoke(id)
        }
    }

}