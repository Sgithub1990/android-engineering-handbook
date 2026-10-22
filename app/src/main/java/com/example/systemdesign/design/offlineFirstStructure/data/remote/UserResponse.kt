package com.example.systemdesign.design.offlineFirstStructure.data.remote

import com.example.systemdesign.design.offlineFirstStructure.data.local.UserEntity

data class UserResponse(
    val users: List<com.example.systemdesign.design.offlineFirstStructure.data.local.UserEntity>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
