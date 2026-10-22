package com.example.systemdesign.design.offlineFirstStructure.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.systemdesign.design.offlineFirstStructure.data.local.UserEntity

@Composable
fun UserRow(userEntity: UserEntity){

    Card(    modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)){

        Column(Modifier.padding(5.dp)){

        Row{
            AsyncImage(modifier = Modifier.size(50.dp),
                model = userEntity.image,
                contentDescription = "")

            Spacer(modifier= Modifier.padding(start = 10.dp))

            Text("Name: " +userEntity.firstName)

        }

        Column(modifier = Modifier.padding(start = 65.dp)) {

            Text("mobile: "+userEntity.phone)
            Text("age: "+userEntity.age)

        }

    }
    }

}