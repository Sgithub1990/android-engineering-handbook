package com.example.systemdesign.design.chatApplication.domain.socket

import com.example.systemdesign.systemdesign.chatApplication.data.local.MessageEntity

sealed interface SocketEvent {

   data object Connect: SocketEvent

   data object Disconnect: SocketEvent

    data class  MessageReceived(
        val message: MessageEntity
    ):SocketEvent

    data class  MessageSent(
        val clientMessageId: Int
    ):SocketEvent

    data class  MessageDelivered(
        val clientMessageId: Int
    ):SocketEvent

    data class MessageRead(
        val clientMessageId: Int
    ) : SocketEvent

    data class Error(
        val throwable: Throwable
    ) : SocketEvent

}