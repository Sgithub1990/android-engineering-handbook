package com.example.systemdesign.design.notificationSystem.data.repository

import com.example.systemdesign.design.notificationSystem.data.local.TokenDataBase
import com.example.systemdesign.design.notificationSystem.data.remote.NotificationApi
import com.example.systemdesign.design.notificationSystem.domain.NotificationRepository
import com.example.systemdesign.design.notificationSystem.domain.Token
import javax.inject.Inject

class NotificationRepoImp @Inject constructor(val dataBase: com.example.systemdesign.design.notificationSystem.data.local.TokenDataBase, val api: com.example.systemdesign.design.notificationSystem.data.remote.NotificationApi):
    com.example.systemdesign.design.notificationSystem.domain.NotificationRepository {


    override suspend fun saveToken(token: String) {
       dataBase.getTokenDao().saveToken(
           Token(
               token
           )
       )
    }

    override suspend fun uploadToken() {
        val token = dataBase.getTokenDao().getToken()
        token?.let {
            api.sendToken(it.token)
            dataBase.getTokenDao().delete()
        }
    }
}