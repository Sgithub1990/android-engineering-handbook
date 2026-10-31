package com.example.systemdesign.design.downloadManager.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.systemdesign.design.downloadManager.data.local.DownloadEntity
import com.example.systemdesign.design.downloadManager.data.local.DownloadStatus

@Composable
fun DownloadItem(
    download: DownloadEntity,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onCancel: () -> Unit,
) {

    val total = download.totalBytes ?: 0L
    val progress =
        if (total > 0) {
            download.downloadBytes.toFloat() / total.toFloat()
        } else {
            0f
        }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // File name
            Text(
                text = download.fileName ?: "Unknown file",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            // Progress bar
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            // Percentage
            Text(
                text = "${(progress * 100).toInt()}%"
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            // Downloaded / Total
            Text(
                text = "${download.downloadBytes} / " +
                        download.totalBytes
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            // Status
            Text(
                text = download.downLoadStatus?.name ?: ""
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            when (download.downLoadStatus) {

                DownloadStatus.DOWNLOADING -> {

                    Row(
                        horizontalArrangement =
                            Arrangement.spacedBy(8.dp)
                    ) {

                        Button(
                            onClick = onPause
                        ) {
                            Text("Pause")
                        }

                        OutlinedButton(
                            onClick = onCancel
                        ) {
                            Text("Cancel")
                        }
                    }
                }

                DownloadStatus.PAUSE -> {

                    Row(
                        horizontalArrangement =
                            Arrangement.spacedBy(8.dp)
                    ) {

                        Button(
                            onClick = onResume
                        ) {
                            Text("Resume")
                        }

                        OutlinedButton(
                            onClick = onCancel
                        ) {
                            Text("Cancel")
                        }
                    }
                }

                DownloadStatus.COMPLETED -> {

                    Text("Download completed")
                }

                DownloadStatus.FAILED -> {

                    Button(
                        onClick = onResume
                    ) {
                        Text("Retry")
                    }
                }

                DownloadStatus.CANCEL -> {

                    Text("Download cancelled")
                }

                else -> {}
            }
        }
    }
}