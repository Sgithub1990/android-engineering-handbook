package com.example.systemdesign.android.security

import kotlinx.coroutines.CoroutineScope

class TokenRepository {
    val accessToken = "1234"


    fun getAccessToken(): String{
        /** get from data store*/
        return  "accessToken"
    }

    fun saveAccessToken(token: String){
    /**save in Data Store*/
    }


   suspend fun refreshToken(): String{
     /**call Api */

     val refreshToken =  "refreshToken"
     saveAccessToken(refreshToken)
     return refreshToken;
    }
}