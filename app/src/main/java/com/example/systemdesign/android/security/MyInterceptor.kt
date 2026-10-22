package com.example.systemdesign.android.security

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor(val token: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        /**get request*/

        /** add  request header*/
        val request = chain.request().newBuilder().header("Authorization","Bearer $token").build()

        /** return response*/
        return chain.proceed(request)
    }
}