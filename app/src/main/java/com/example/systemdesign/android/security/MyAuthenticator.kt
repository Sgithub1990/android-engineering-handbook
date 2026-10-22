package com.example.systemdesign.android.security

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class MyAuthenticator(val tokenManager: TokenRepository): Authenticator {
    private val mutex = Mutex()
    override fun authenticate(route: Route?, response: Response): Request? {

        /** do not retry infinitely if failed repeatedly*/
        if(responseCount(response)>2){
            return null
        }

        /**get failed token */
        val failedToken = response.request.header("Authorization")?.removePrefix("Bearer ")?.trim()
        val currentToken = tokenManager.accessToken

        if(failedToken!=currentToken){
            /**means token has been already refreshed by other request*/

            return response.request.newBuilder().header("Authorization", "Bearer $currentToken").build()
         }else{

             /** use run blocking for background work */
             val refreshToken =
                runBlocking {
                    mutex.withLock {
                        tokenManager.refreshToken();
                    }

               }
            return response.request.newBuilder().header("Authorization", "Bearer $refreshToken").build()

        }


    }
    fun  responseCount(response: Response): Int{
        var  count = 1
        var current = response.priorResponse
        while (current!=null){
            count++;
            current = current.priorResponse;

        }

        return  count;

    }


}