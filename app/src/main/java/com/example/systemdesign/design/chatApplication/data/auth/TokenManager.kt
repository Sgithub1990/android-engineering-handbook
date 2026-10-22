package com.example.systemdesign.design.chatApplication.data.auth

import javax.inject.Inject

class TokenManager @Inject constructor() {
    var token: String?=null
    var refreshToken: String?=null

   fun  token(token: String)
   {
       this.token = token
   }

    fun refreshToken(): String?{
      return  refreshToken;
    }

    fun token(): String?{
       return token
    }






}