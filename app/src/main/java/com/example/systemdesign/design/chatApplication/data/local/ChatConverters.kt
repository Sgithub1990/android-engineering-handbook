package com.example.systemdesign.design.chatApplication.data.local

import androidx.room.TypeConverter

class ChatConverters {
    @TypeConverter
    fun fromMessageStatus(status: MessageStatus?): Int? {
        return status?.id
    }

    @TypeConverter
    fun toMessageStatus(id: Int?): MessageStatus? {
        return id?.let { MessageStatus.fromId(it) }
    }
}
