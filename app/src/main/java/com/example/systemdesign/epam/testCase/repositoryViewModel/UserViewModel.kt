package com.example.systemdesign.epam.testCase.repositoryViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(val repository: TRepository): ViewModel() {

    val _user = MutableStateFlow<List<TUser>?>(null)
    val user = _user.asStateFlow()


    fun loadUser(){
        viewModelScope.launch {
            val result = repository.getUser()
            _user.value = result
        }

    }



}