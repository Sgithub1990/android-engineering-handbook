package com.example.systemdesign.epam.testCase.repositoryViewModel

import javax.inject.Inject

class TRepository @Inject constructor( val api: TUserApi) {

    suspend fun getUser(): List<TUser>{
       return api.getUser()
    }
}