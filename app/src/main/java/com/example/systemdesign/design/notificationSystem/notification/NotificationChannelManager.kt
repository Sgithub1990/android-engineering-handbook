package com.example.systemdesign.design.notificationSystem.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

object NotificationChannelManager {

    fun createNotificationChannel(context: Context){
            if(Build.VERSION.SDK_INT> Build.VERSION_CODES.O){
                val notificationChannel = NotificationChannel(
                    NotificationConstant.notification_channel_id,
                    NotificationConstant.notification_channel_name,
                    NotificationManager.IMPORTANCE_HIGH

                )

                val manager = context.getSystemService(NotificationManager::class.java)
                manager.createNotificationChannel(notificationChannel)
           }
    }

}