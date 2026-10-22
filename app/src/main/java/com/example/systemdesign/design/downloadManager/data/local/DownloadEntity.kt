package com.example.systemdesign.design.downloadManager.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "download_table")
data class DownloadEntity(
    @PrimaryKey
    val id: String = "",
    val url: String?="",
    val fileName: String?="",
    val filePath: String?="",
    val downloadBytes: Long =0,
    val totalBytes: Long?=0,
    val downLoadStatus: DownloadStatus?= DownloadStatus.DOWNLOADING,
    val error: String?=""
)