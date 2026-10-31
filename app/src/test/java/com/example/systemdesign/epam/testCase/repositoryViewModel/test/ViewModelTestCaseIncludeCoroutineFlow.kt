package com.example.systemdesign.epam.testCase.repositoryViewModel.test

import com.example.systemdesign.epam.testCase.repositoryViewModel.TRepository
import com.example.systemdesign.epam.testCase.repositoryViewModel.TUser
import com.example.systemdesign.epam.testCase.repositoryViewModel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTestCaseIncludeCoroutineFlow {
    lateinit var repository: TRepository
    lateinit var testDispatcher: TestDispatcher
    private lateinit var viewModel: UserViewModel


    @Before
    fun setUp(){
        testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)

        repository = mock()
        viewModel = UserViewModel(repository)
    }
    @Test
    fun getUser()= runTest(testDispatcher) {
        val expected = listOf(TUser(101,"shyam","8285809188"))
        whenever(repository.getUser()).thenReturn(expected)

        viewModel.loadUser()
        advanceUntilIdle()

        assertEquals(expected,viewModel._user.value)
        verify(repository.getUser())
    }
    @After
    fun reset(){
        Dispatchers.resetMain()
    }

}
