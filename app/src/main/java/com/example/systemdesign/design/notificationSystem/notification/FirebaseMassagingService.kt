package com.example.systemdesign.design.notificationSystem.notification

import android.Manifest
import androidx.annotation.RequiresPermission
import com.example.systemdesign.design.notificationSystem.domain.NotificationRepository
import com.example.systemdesign.design.notificationSystem.worker.SyncScheduler
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FirebaseMassagingService : FirebaseMessagingService() {

    @Inject
    lateinit var repository: NotificationRepository

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val title = message.data["title"] ?: "Notification"
        val body = message.data["body"] ?: ""
        NotificationHelper.showPaymentNotification(context = applicationContext, title = title, body = body)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        CoroutineScope(Dispatchers.IO).launch {
            repository.saveToken(token)
            SyncScheduler.enqueue(applicationContext)
        }
    }
}
