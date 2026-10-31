package com.example.systemdesign.epam.testCase.MockFake.fake

import com.example.systemdesign.epam.testCase.MockFake.MyViewModel
import com.example.systemdesign.epam.testCase.MockFake.UState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FakeTest {
    lateinit var testDispatcher: TestDispatcher
    lateinit var repository: FakeRepository
    lateinit var viewModel: MyViewModel


    @Before
    fun setUp(){
        repository = FakeRepository()
        viewModel = MyViewModel(repository)

        testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testSuccessCase()= runTest(testDispatcher) {
      viewModel.getLoginStatus("sh@gmail.com",1234)
        advanceUntilIdle()
      val expected = UState.Success
      val actual = viewModel.uiState.value
        assertEquals(expected,actual)
    }

    @Test
    fun testFailCase()= runTest(testDispatcher) {
        viewModel.getLoginStatus("fail@gmail.com",1234)
        advanceUntilIdle()
        val expected = UState.Failed("some thing went wrong")
        val actual = viewModel.uiState.value
        assertEquals(expected,actual)
    }


}
