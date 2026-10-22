package com.example.systemdesign.design.downloadManager.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.systemdesign.design.downloadManager.presentation.viewmodel.DownloadViewModel

@Composable
fun DownloadScreen() {

    val viewModel: DownloadViewModel = hiltViewModel()

    val downloads by viewModel.allDownload
        .collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // Start Download
        Button(
            onClick = {
                viewModel.startDownload(
                    url = "https://httpbin.org/range/100000000",
                    fileName = "test.bin"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Start Download")
        }


        // Download list
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(
                items = downloads,
                key = { it.id!! }
            ) { download ->

                DownloadItem(
                    download = download,

                    onPause = {
                        viewModel.pause(download.id!!)
                    },

                    onResume = {
                        viewModel.resume(download.id!!)
                    },

                    onCancel = {
                        viewModel.cancel(download.id!!)
                    }
                )
            }
        }
    }
}