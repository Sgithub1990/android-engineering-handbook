package com.example.systemdesign.design.chatApplication.domain.repository

import com.example.systemdesign.design.chatApplication.data.local.MessageEntity
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun observeMsg(conversationId: Int): Flow<List<MessageEntity>>

    suspend fun senMsg(conversationId: Int,
                       senderId: Int,
                       receiverId: Int,
                       text: String)

    suspend fun syncMessages(conversationId: Int)

    suspend fun getAllFailedMsg(): List<MessageEntity>

    fun connectSocket()
    fun disConnectSocket()

    suspend fun resendMsg(message: MessageEntity)






}