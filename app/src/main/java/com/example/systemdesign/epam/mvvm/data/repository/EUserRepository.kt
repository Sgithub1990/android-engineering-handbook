package com.example.systemdesign.epam.mvvm.data.repository

import com.example.systemdesign.epam.mvvm.model.EUser

interface EUserRepository {

    suspend fun getUser(): List<EUser>
}