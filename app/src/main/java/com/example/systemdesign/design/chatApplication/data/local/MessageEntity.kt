package com.example.systemdesign.design.chatApplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message")
data class MessageEntity (
    @PrimaryKey(autoGenerate = true)
    val clientMsgId: Int = 0,
    val serverMsgId: Int,
    val conversationId: Int,
    val sendId: Int,
    val receiverId: Int,
    val msg: String,
    val timeStamp: Long,
    val status: com.example.systemdesign.design.chatApplication.data.local.MessageStatus
)
