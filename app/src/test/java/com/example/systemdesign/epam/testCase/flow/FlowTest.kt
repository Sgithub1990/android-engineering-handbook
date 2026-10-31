package com.example.systemdesign.epam.testCase.flow

import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class FlowTest {
    lateinit var flowUtil: FlowUtil

    @Before
    fun setUp(){
      flowUtil = FlowUtil()
    }

    @Test
    fun flowTest() = runTest {
        val result = flowUtil.flow().toList()
        assertEquals(listOf(4,5),result)
    }

    @Test
    fun flowTestTurbine() = runTest {
        flowUtil.flow().test {
            assertEquals(4, awaitItem())
            assertEquals(5, awaitItem())
            awaitComplete()
        }
    }
}
