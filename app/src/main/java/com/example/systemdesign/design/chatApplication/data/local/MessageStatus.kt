package com.example.systemdesign.design.chatApplication.data.local

enum class MessageStatus(val id: Int) {
    SENDING(1),
    SENT(2),
    DELIVERED(3),
    READ(4),
    FAILED(5);


    companion object {
        private val map = entries.associateBy { it.id }

        fun fromId(id: Int): MessageStatus? = map[id]
    }
}