package com.example.systemdesign.design.instagramFeed.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_key")
data class RemoteKeyEntity(
     @PrimaryKey
     val id: Int,
     val pre: Int?,
     val next: Int?
)
