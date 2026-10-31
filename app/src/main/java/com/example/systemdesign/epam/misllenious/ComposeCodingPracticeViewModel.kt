package com.example.systemdesign.epam.misllenious

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ComposeCodingPracticeViewModel @Inject constructor(): ViewModel() {

   private val _user = MutableStateFlow<UserProfile?>(null)
    val user = _user.asStateFlow()

    suspend fun callApi(){
        _user.value = UserProfile(userId = 100, userName = "Shyam",8285809188)

    }

}