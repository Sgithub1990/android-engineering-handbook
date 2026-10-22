package com.example.systemdesign

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.example.systemdesign.design.notificationSystem.notification.NotificationChannelManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SystemDesignApp : Application(), Configuration.Provider{

    @Inject
    lateinit var workerFactory: HiltWorkerFactory
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        NotificationChannelManager.createNotificationChannel(applicationContext)

        /*load native code for SQL cipher*/
        System.loadLibrary("sqlcipher")

    }

}
