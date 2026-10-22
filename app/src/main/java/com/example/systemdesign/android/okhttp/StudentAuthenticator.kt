package com.example.systemdesign.android.okhttp

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class StudentAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if(responseCount(response)>3){
            return null;
        }
        if(TokenManager.refreshToken==null){
            return null
        }
        return response.request.newBuilder().header("Authorization","Bearer "+ TokenManager.newToken).build()
    }

    fun  responseCount(response: Response): Int{
        var count = 1
        var previousResponse =  response.priorResponse

       while (previousResponse!=null){
          count++;
          previousResponse = previousResponse.priorResponse;
       }
    return  count
    }

}