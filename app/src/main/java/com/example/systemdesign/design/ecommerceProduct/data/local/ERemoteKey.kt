package com.example.systemdesign.design.ecommerceProduct.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eremote")
data class ERemoteKey(
    @PrimaryKey
    val id: Int,
    val pre: Int?,
    val next: Int?)
