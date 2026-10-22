package com.example.systemdesign.android.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface StudentDao {

    @Insert
    suspend fun insertStudent(students: List<StudentEntity>)
    @Query("select * from student")
    fun getAllStudent(): Flow<List<StudentEntity>>

    @Query("select * from student where id=:id")
    suspend fun getStudent(id: Long): StudentEntity

    @Query("delete  from student where rollNumber=:rollNumber")
    suspend fun deleteStudent(rollNumber: Long): Int

    @Query("update student set rollNumber=:rollNumber where rollNumber=:oldRollNumber")
    suspend fun updateRollNumber(oldRollNumber:Long,rollNumber: Long): Int

    @Query("delete  from student")
    suspend fun deleteAllStudent()











}