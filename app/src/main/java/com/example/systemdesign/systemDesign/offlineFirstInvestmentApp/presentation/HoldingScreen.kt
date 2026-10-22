package com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local.SyncStatus


@Composable
fun HoldingScreen(){

    val viewModel: InvViewModel = hiltViewModel()

    val listHolding by viewModel.result.collectAsState()
    val error by viewModel.syncStatus.collectAsState()

    Column {
        if (error== SyncStatus.FAILED) {
            Text(
                text = error.name,
                color = Color.Red
            )
        }

     LazyColumn(modifier = Modifier) {

     items(items = listHolding, key = {holding-> holding.holdingUUId} ){
       HoldingItemView(it)

     }

    }
    }

}