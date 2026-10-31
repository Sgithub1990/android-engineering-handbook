package com.example.systemdesign.epam.testCase.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class TestDataBase {
    lateinit var context: Context
    lateinit var database: MovieDatabase
    lateinit var dao: MovieDao

    @Before
    fun setUp(){
     context = ApplicationProvider.getApplicationContext()
     database = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java).build()
     dao = database.movieDao()
     }

    @Test
    fun testDataBase(){
      val movie = MovieEntity(1,"LOC")
      dao.insertMovie(listOf(movie))

     val result = dao.getAllMovie()
        assertEquals(listOf(movie),result)

    }

    @After
    fun tear(){
        database.close()
    }
}
