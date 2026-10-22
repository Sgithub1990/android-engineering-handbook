package com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local.SyncStatus
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.domain.repository.InvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvViewModel @Inject constructor(val repository: InvRepository): ViewModel() {

    private val _syncStatus = MutableStateFlow(SyncStatus.PENDING)
    val syncStatus = _syncStatus.asStateFlow()

  val userId = 1001L

   init {
       viewModelScope.launch {
           repository.refreshHolding(userId)
       }

   }

    val result = repository.getAllHolding(userId).stateIn(
    scope =  viewModelScope,
    started = SharingStarted.WhileSubscribed(),
    initialValue = emptyList()
    )


    fun observeSyncStatus() {
        viewModelScope.launch {
            repository.syncStatus()?.collect { status ->
                _syncStatus.value = status
            }
        }
    }

}