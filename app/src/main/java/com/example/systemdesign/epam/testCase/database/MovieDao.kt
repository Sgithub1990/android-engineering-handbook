package com.example.systemdesign.epam.testCase.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieList:List<MovieEntity>)

    @Query("select * from movie")
    fun getAllMovie(): List<MovieEntity>
}