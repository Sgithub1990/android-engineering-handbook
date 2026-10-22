package com.example.systemdesign.design.chatApplication.data.repository

import com.example.systemdesign.design.chatApplication.data.local.MessageDao
import com.example.systemdesign.design.chatApplication.data.local.MessageEntity
import com.example.systemdesign.design.chatApplication.data.local.MessageStatus
import com.example.systemdesign.design.chatApplication.data.remote.ApiService
import com.example.systemdesign.design.chatApplication.data.remote.WebSocket
import com.example.systemdesign.design.chatApplication.data.sync.ScheduleWorker
import com.example.systemdesign.design.chatApplication.domain.repository.ChatRepository
import com.example.systemdesign.design.chatApplication.domain.socket.SocketEvent
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class ChatRepositoryImp @Inject constructor(
    private val msgDao: MessageDao,
    private val webSocket: WebSocket,
    private val apiService: ApiService,
    private val scheduler: ScheduleWorker
) : ChatRepository {

    override fun observeMsg(conversationId: Int): Flow<List<MessageEntity>> {
        return msgDao.observeMsg(conversationId)
    }

    override suspend fun senMsg(
        conversationId: Int,
        senderId: Int,
        receiverId: Int,
        text: String
    ) {
        /*first save locally*/
        try {
            val message = MessageEntity(
                clientMsgId = Random.nextInt(),
                serverMsgId = -1,
                conversationId = conversationId,
                sendId = senderId,
                receiverId = receiverId,
                msg = text,
                status = MessageStatus.SENDING,
                timeStamp = System.currentTimeMillis()
            )
            msgDao.insertMessage(message)
            /*send to websocket*/
            webSocket.webSocket?.send(Gson().toJson(message))
        } catch (e: Exception) {
            scheduler.enqueueMessage()
        }
    }

    override suspend fun syncMessages(conversationId: Int) {
        withContext(Dispatchers.IO) {
            try {
                val allChat = apiService.getAllChat(conversationId)
                msgDao.insertAll(allChat)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun getAllFailedMsg(): List<MessageEntity> {
        return msgDao.getMessagesByStatus(MessageStatus.FAILED)
    }

    override fun connectSocket() {
        webSocket.connect()
    }

    override fun disConnectSocket() {
        webSocket.disconnect()
    }

    override suspend fun resendMsg(message: MessageEntity) {
        webSocket.webSocket?.send(Gson().toJson(message))
    }

    private suspend fun handleEvent(event: SocketEvent) {
        when (event) {
            is SocketEvent.MessageReceived -> {
                msgDao.insertMessage(event.message)
            }
            is SocketEvent.MessageSent -> {
                msgDao.updateStatus(
                    clientMsgId = event.clientMessageId,
                    status = MessageStatus.SENT
                )
            }
            is SocketEvent.MessageDelivered -> {
                msgDao.updateStatus(
                    clientMsgId = event.clientMessageId,
                    status = MessageStatus.DELIVERED
                )
            }
            is SocketEvent.MessageRead -> {
                msgDao.updateStatus(
                    clientMsgId = event.clientMessageId,
                    status = MessageStatus.READ
                )
            }
            else -> {}
        }
    }
}
