package com.example.systemdesign.epam.mvvm.data.remote

import com.example.systemdesign.epam.mvvm.model.EUser
import retrofit2.http.GET

interface EUserApi {

    @GET("user")
    fun getUser(): List<EUser>
}