package com.example.systemdesign.design.notificationSystem.data.remote

interface NotificationApi {
    suspend fun sendToken(token: String)
}