package com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local.HoldingEntity


@Composable
fun HoldingItemView( holdingEntity: HoldingEntity){

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(modifier = Modifier, verticalAlignment =Alignment.CenterVertically ) {
            Text(holdingEntity.fundName)
            Text(""+holdingEntity.investedAmount)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(""+holdingEntity.investedAmount)






    }

}