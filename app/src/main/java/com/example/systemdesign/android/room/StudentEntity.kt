package com.example.systemdesign.android.room

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "student", indices = [Index(value = ["college"])])
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String?="",
    val college: String?="",
    val fee: Long?=0,
    val rollNumber: Long?=0,
    val motherName: String?="",
    val fatherName: String?=""
)
