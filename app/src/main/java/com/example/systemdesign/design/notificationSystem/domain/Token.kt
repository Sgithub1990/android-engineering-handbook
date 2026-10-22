package com.example.systemdesign.design.notificationSystem.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token")
data class Token(
    @PrimaryKey
    val token: String
)
