package com.example.systemdesign.design.downloadManager.data.local

import androidx.room.TypeConverter

object DownLoadStatusTypeConverter {

    @TypeConverter
    fun fromDownLoadStatus(status: DownloadStatus?): String?{
        return status?.name
    }

    @TypeConverter
    fun toDownLoadStatus(name: String?): DownloadStatus?{
        return name?.let { DownloadStatus.valueOf(it) }
    }
}
