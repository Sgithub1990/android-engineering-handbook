package com.example.systemdesign.epam.testCase.repositoryViewModel.test
import com.example.systemdesign.epam.testCase.repositoryViewModel.TRepository
import com.example.systemdesign.epam.testCase.repositoryViewModel.TUser
import com.example.systemdesign.epam.testCase.repositoryViewModel.TUserApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


class RepositoryTestCase {
lateinit var api : TUserApi
lateinit var repository: TRepository

  @Before
  fun setUp(){
    api = mock()
    repository  = TRepository(api)
  }
  @Test
  fun getUsers() = runTest{
    val expected = listOf(TUser(101,"shyam","8285809188"))
    whenever(api.getUser()).thenReturn(expected)
    val actual = repository.getUser()
    assertEquals(expected,actual)
    verify(api.getUser())
  }
}
