package com.example.systemdesign.epam.testCase.MockFake

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    val _uiState = MutableStateFlow<UState>(UState.Loading)
    val uiState = _uiState.asStateFlow()


   suspend fun getLoginStatus(email: String,pass: Int){
        val isLogin = repository.login(email,pass)
        if(isLogin){
            _uiState.value = UState.Success
        }else{
            _uiState.value = UState.Failed("some thing went wrong")
        }
    }


}


   sealed interface  UState {
       data object Loading:UState
       data class Failed(val error: String):UState
       data object Success:UState

   }