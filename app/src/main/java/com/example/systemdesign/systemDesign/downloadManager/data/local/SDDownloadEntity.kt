package com.example.systemdesign.systemDesign.downloadManager.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sddClient")
data class SDDownloadEntity(
    @PrimaryKey(autoGenerate = true)
    val downloadId: Long = 0,
    val url: String,
    val fileName: String,
    val filePath: String,
    val progress: Int,
    val downloadedByte: Long,
    val totalBytes: Long
)
