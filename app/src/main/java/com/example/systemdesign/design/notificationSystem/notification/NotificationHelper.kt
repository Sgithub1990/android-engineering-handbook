package com.example.systemdesign.design.notificationSystem.notification

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.systemdesign.MainActivity
import com.example.systemdesign.R

object NotificationHelper {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun showPaymentNotification(context: Context, title: String, body: String){

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_MUTABLE
        )

        val notification = NotificationCompat.Builder(context,NotificationConstant.notification_channel_id)
             .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText(body)
            .setChannelId(NotificationConstant.notification_channel_id)
            .setContentIntent(pendingIntent)
            .build()
        NotificationManagerCompat.from(context).notify(NotificationConstant.notification_id,notification)

    }

}