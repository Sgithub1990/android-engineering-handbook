package com.example.systemdesign.design.downloadManager.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DownloadDao {

    /*insert download entity*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(downloadEntity: DownloadEntity)

    /*get download */

    @Query("select * from download_table limit 1")
    fun getDownLoad(): Flow<DownloadEntity?>

    @Query("select * from download_table")
    fun getAllDownLoad(): Flow<List<DownloadEntity>>

    @Query("select * from download_table where id=:id")
    suspend fun getDownLoadById(id: String): DownloadEntity?

    /*update download */

    @Query("Update download_table set downloadBytes=:bytes, downLoadStatus=:status, totalBytes=:total, fileName=:name, filePath=:path where id=:id")
    suspend fun updateProgress(
        id: String,
        bytes: Long,
        total: Long?,
        name: String?,
        path: String?,
        status: DownloadStatus?
    ): Int

    @Query("Update download_table set downLoadStatus=:status, error=:error where id=:id")
    suspend fun updateDownLoadStatus(
        id: String,
        status: DownloadStatus?,
        error: String?
    ): Int
}