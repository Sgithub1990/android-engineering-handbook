package com.example.systemdesign.android.okhttp

import okhttp3.Interceptor
import okhttp3.Response

class StudentInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
       val newRequest =  chain.request().newBuilder().header("Authorization ","Bearer "+ TokenManager.refreshToken).build();
        return chain.proceed(newRequest)
    }
}