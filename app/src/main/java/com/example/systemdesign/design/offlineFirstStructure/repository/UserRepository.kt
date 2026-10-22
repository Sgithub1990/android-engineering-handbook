package com.example.systemdesign.design.offlineFirstStructure.repository

import com.example.systemdesign.design.offlineFirstStructure.data.local.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {


    fun getUsers(): Flow<List<UserEntity>>

    suspend fun getUserFromRemote(): List<UserEntity>

    suspend fun enQueWork()

    suspend fun insertUser(users: List<UserEntity>)

}