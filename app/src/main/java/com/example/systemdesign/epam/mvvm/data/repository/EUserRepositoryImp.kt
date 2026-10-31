package com.example.systemdesign.epam.mvvm.data.repository

import com.example.systemdesign.epam.mvvm.data.remote.EUserApi
import com.example.systemdesign.epam.mvvm.model.EUser
import javax.inject.Inject

class EUserRepositoryImp @Inject constructor(val api: EUserApi): EUserRepository {
    override suspend fun getUser(): List<EUser> {
      return api.getUser()
    }
}