package com.example.systemdesign.design.offlineFirstStructure.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
public data class UserEntity(
    @PrimaryKey
    val id: Int,
    val firstName: String,
    val age: Int,
    val gender: String,
    val email: String,
    val phone: String,
    val image: String,
    val accountNum: String
)
