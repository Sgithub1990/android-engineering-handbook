package com.example.systemdesign.design.chatApplication.data.remote

import com.example.systemdesign.design.chatApplication.data.local.MessageEntity
import retrofit2.http.GET

interface ApiService {

    @GET
    fun getAllChat(conversationId: Int): List<com.example.systemdesign.design.chatApplication.data.local.MessageEntity>
}