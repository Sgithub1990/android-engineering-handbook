package com.example.systemdesign.systemDesign.paymentflow.presentation.payment

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.systemdesign.systemDesign.paymentflow.data.local.ApiState


@Composable
fun PaymentScreen(viewModel: PaymentViewModel){

    LaunchedEffect(Unit) {
        viewModel.handlePayment()
    }
    val orderResult by viewModel.order.collectAsState()

    when(orderResult){
        is ApiState.Error -> {

        }
        ApiState.Loading ->{
            CircularProgressIndicator()
        }
        is ApiState.Success ->{

        }
    }

}