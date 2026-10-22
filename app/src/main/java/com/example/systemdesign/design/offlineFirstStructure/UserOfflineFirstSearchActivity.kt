package com.example.systemdesign.design.offlineFirstStructure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.systemdesign.design.offlineFirstStructure.presentation.screens.UsersScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserOfflineFirstSearchActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                UsersScreen(
                    modifier = Modifier.Companion
                )

            }


        }
    }
}