package com.example.systemdesign.design.notificationSystem.domain

interface NotificationRepository {
    suspend fun saveToken(token: String)

    suspend fun uploadToken()
}