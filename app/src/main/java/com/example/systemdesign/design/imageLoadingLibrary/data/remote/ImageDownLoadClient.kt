package com.example.systemdesign.design.imageLoadingLibrary.data.remote

import okhttp3.OkHttpClient
import okhttp3.Request

 class ImageDownLoadClient(val client: OkHttpClient) {

    suspend fun downLoad(url: String): ByteArray?{
        val request = Request.Builder().url(url).build();
        val response = client.newCall(request).execute()
        if(!response.isSuccessful){
            return null;
        }else{
            return response.body.bytes()
        }

    }

}