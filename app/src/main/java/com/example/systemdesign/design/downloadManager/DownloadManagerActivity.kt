package com.example.systemdesign.design.downloadManager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.systemdesign.design.downloadManager.presentation.screen.DownloadScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DownloadManagerActivity : ComponentActivity() {
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        setContent {
            MaterialTheme() {
                DownloadScreen()

            }
        }
    }
}