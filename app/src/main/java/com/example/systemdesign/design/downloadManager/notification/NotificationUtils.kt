package com.example.systemdesign.design.downloadManager.notification

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.ForegroundInfo

object NotificationUtils {

    fun createForeGroundInfo(context: Context,fileName: String,progress: Int ): ForegroundInfo{
  val notification= NotificationCompat.Builder(context,"download")
                     .setContentTitle("Download$fileName")
                     .setContentText( "$progress% downloaded")
                     .setProgress(100,progress,false)
                     .setOngoing(true)
                     .setCategory( NotificationCompat.CATEGORY_PROGRESS)
                     .build()

        return ForegroundInfo(1001,notification)
    }


}