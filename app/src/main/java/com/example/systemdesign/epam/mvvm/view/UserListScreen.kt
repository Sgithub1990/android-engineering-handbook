package com.example.systemdesign.epam.mvvm.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.systemdesign.epam.mvvm.viewModel.EUserViewModel
import com.example.systemdesign.epam.mvvm.viewModel.UIStatus


@Composable
fun UserList(){
    val viewModel = hiltViewModel<EUserViewModel>()
    val userList by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getUserList()
    }
    when(userList){
         is UIStatus.ERROR -> {
             /*show error*/
         }
         UIStatus.LOADING -> {
             CircularProgressIndicator()
         }
         is UIStatus.SUCCESS -> {
             Column {

                 val users = (userList as UIStatus.SUCCESS).users
                 LazyColumn{
                     items(items = users,key= { users-> users.id!! }){

                     }

                 }

             }
         }

     }
}