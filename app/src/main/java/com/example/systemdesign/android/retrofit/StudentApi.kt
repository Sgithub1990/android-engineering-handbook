package com.example.systemdesign.android.retrofit

import com.example.systemdesign.android.room.StudentEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentApi {
    /**https://example.com/api/students?page=2*/

    @GET("students")
    fun getAllStudent(
     @Query("id") id:Int):List<StudentEntity>
}


