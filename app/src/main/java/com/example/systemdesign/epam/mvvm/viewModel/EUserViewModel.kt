package com.example.systemdesign.epam.mvvm.viewModel

import androidx.lifecycle.ViewModel
import coil.network.HttpException
import com.example.systemdesign.epam.mvvm.data.repository.EUserRepository
import com.example.systemdesign.epam.mvvm.model.EUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EUserViewModel @Inject constructor(val repository: EUserRepository): ViewModel(){

    val _uiState = MutableStateFlow<UIStatus>(UIStatus.LOADING)
    val uiState: StateFlow<UIStatus>  = _uiState.asStateFlow()




    suspend fun getUserList(){
        _uiState.value = UIStatus.LOADING
        try {
           val result =  repository.getUser()
            _uiState.value = UIStatus.SUCCESS(result)
        }catch (e: HttpException){
            _uiState.value = UIStatus.ERROR(e.toString())
        }
    }


}

sealed interface UIStatus{
    data object  LOADING: UIStatus
    data class  SUCCESS(val users: List<EUser>): UIStatus
    data class ERROR(val error: String): UIStatus

}
