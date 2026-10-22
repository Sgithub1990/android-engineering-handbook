package com.example.systemdesign.android.jetpackCompose

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

@HiltViewModel
class JCViewModel: ViewModel() {


    fun apiCall(): Response{
        val client = OkHttpClient()
        val request = Request.Builder().url("").build()
        val requestCall  = client.newCall(request)
        val response = requestCall.execute()
        return response;
    }








}