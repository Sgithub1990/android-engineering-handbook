package com.example.systemdesign.epam.testCase.sampleTest

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

class Test {

    @Test
    fun addTest(){
        val utils = Utils()
        val actual =  utils.add(2,3)
        val expected = 5;
        assertEquals(expected,actual)
    }
}


@RunWith(Parameterized::class)
class ParameterizeTestCase(val a: Int,val b: Int,val expected: Int){
    lateinit var utils: Utils

    @Before
    fun setUp(){
        utils = Utils()
    }
    @Test
    fun addTest(){
        assertEquals(expected,utils.add(a,b)  )

    }

    companion object{
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Array<Any>>{
           return listOf(
              arrayOf(2,3,5),
               arrayOf(2,3,5),
               arrayOf(0,3,3)

           )
        }
    } }
