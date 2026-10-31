package com.example.systemdesign.epam.testCase.MockFake.mock

import com.example.systemdesign.epam.testCase.MockFake.MyViewModel
import com.example.systemdesign.epam.testCase.MockFake.Repository
import com.example.systemdesign.epam.testCase.MockFake.UState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class MockTest {
  lateinit var testDispatcher: TestDispatcher
  lateinit var repository: Repository
  lateinit var viewModel: MyViewModel

  @Before
    fun setUp(){
       testDispatcher = StandardTestDispatcher()
       repository = mock()
       viewModel = MyViewModel(repository)

      Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testSuccessCase()= runTest(testDispatcher) {
        whenever(repository.login("sh@gmail.com",1234)).thenReturn(true)

        viewModel.getLoginStatus("sh@gmail.com",1234)

        advanceUntilIdle()

        val actual = viewModel.uiState.value
        val expected = UState.Success

        assertEquals(expected,actual)
    }

    @Test
    fun testFailCase()= runTest(testDispatcher) {
        whenever(repository.login("sh@gmail.com",1234)).thenReturn(false)
        viewModel.getLoginStatus("sh@gmail.com",1234)
        advanceUntilIdle()
        val actual = viewModel.uiState.value
        val expected = UState.Failed("some thing went wrong")
        assertEquals(expected,actual)
    }


    @After
    fun cleanUp(){
        Dispatchers.resetMain()
    }



}
