package com.example.systemdesign.design.chatApplication.data.auth

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterCeptor @Inject constructor(val tokenManager: com.example.systemdesign.design.chatApplication.data.auth.TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.header("Authentication", "Bearer ${tokenManager.token}")
        request.header("Authentication", "Bearer ${tokenManager.refreshToken}")

        return  chain.proceed(request.build())
    }
}