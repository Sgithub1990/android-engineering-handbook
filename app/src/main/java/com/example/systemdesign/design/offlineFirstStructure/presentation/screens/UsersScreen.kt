package com.example.systemdesign.design.offlineFirstStructure.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.systemdesign.design.offlineFirstStructure.presentation.UserViewModel

@Composable
fun UsersScreen(modifier: Modifier){
    val viewModel: UserViewModel = hiltViewModel()

    val users by viewModel.users.collectAsState()



    Column(modifier) {

        LazyColumn{
            items(users){ user->
                UserRow(user)
            }

        }

    }

}