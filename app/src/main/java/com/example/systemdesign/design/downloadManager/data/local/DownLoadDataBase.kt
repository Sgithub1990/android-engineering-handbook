package com.example.systemdesign.design.downloadManager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DownloadEntity::class], version = 1)
@TypeConverters(DownLoadStatusTypeConverter::class)
abstract class DownLoadDataBase: RoomDatabase() {
    abstract fun downloadDao(): DownloadDao
}