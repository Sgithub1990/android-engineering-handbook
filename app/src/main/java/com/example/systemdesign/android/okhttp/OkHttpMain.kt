package com.example.systemdesign.android.okhttp

import android.content.Context
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.util.concurrent.TimeUnit


class OkHttpMain {

    fun createHttpClient(): OkHttpClient{
          return OkHttpClient.Builder()
                .addInterceptor(StudentInterceptor())
                .authenticator(StudentAuthenticator())
                .certificatePinner(CertificatePinning().addCertificate())
                .build()


    }


    fun createRequestResponse(client: OkHttpClient): Response?{
        val request = Request.Builder()
                     .header("","")
                     .url("")
                     .build()

       val requestCall = client.newCall(request)

        val response = requestCall.execute()

        if(response.isSuccessful){
            return response
        }

        return  null
    }


    fun httpCache(context: Context,client: OkHttpClient): OkHttpClient{
        val cacheSize = 10L*1024*1024 /*10 Mb*/
        val cache = Cache(File(context.cacheDir,"http_cache"),cacheSize)
        return client.newBuilder().cache(cache).build()
    }


    fun httpCacheControl(request: Request){
        val cacheControl = CacheControl.Builder().maxAge(10, TimeUnit.DAYS).build()
        request.newBuilder().cacheControl(cacheControl).build()
    }



















}