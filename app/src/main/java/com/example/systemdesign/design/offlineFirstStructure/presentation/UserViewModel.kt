package com.example.systemdesign.design.offlineFirstStructure.presentation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.systemdesign.design.offlineFirstStructure.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(repository: UserRepository) : ViewModel() {



    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.enQueWork()
        }
    }

    val users = repository.getUsers().stateIn(viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList())





}