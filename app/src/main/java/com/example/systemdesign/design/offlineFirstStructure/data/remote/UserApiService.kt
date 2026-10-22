package com.example.systemdesign.design.offlineFirstStructure.data.remote

import com.example.systemdesign.design.offlineFirstStructure.data.local.UserEntity
import retrofit2.http.GET

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): com.example.systemdesign.design.offlineFirstStructure.data.remote.UserResponse
}