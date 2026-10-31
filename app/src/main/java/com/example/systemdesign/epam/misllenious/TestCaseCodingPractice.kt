package com.example.systemdesign.epam.misllenious

/**
🔥Explain Test Step of Repository using Mockito

🔸 Before -> use for set up before test begin
 1- mock Api, init repository with mock data

🔸 Test
1- use Expected Data
2- use Actual Result Data
3- explain steps
whenEver().thenResult()
4 - check with AsserEqual(expected, actual)
5- verify api call
verify(mockApi.getData)

🔸 After -> use for release resource  after test
 * */


/**
🔥Remember

- Mockito creates mock dependencies.
- whenever(...).thenReturn(...) defines the mock's behavior.
- verify(...) checks interactions.
- assertEquals(...) checks the result.

 */

/**
🔥Explain complete Test Step of Repository -> ViewModel -> coroutine -> flow

class ViewModelTestCaseIncludeFlow {
 var repository: TRepository
 var testDispatcher: TestDispatcher
 var viewModel: UserViewModel

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


🔸why use TestDispatcher
TestDispatcher to control when coroutines execute during unit testing

🔸why use advanceUntilIdle()
advanceUntilIdle() to execute all pending coroutine tasks scheduled on the test scheduler

*/


/**
🔥Perform Compose UI test for login

@get:Rule
val composeTestRule = createComposeRule()

@Test
fun enteringEmailAndClickingLogin_showsSuccess() {
composeTestRule.setContent { LoginScreen() }
composeTestRule.onNodeWithText("Email").performTextInput("test@example.com")
composeTestRule.onNodeWithText("Login").performClick()
composeTestRule.onNodeWithText("Login successful").assertIsDisplayed()

}

* */


/**
 🔥How do you test coroutine throw exception
 🔸 Use AssertFailWith<Exception>
 val exception = assertFailsWith<RuntimeException> {
        repository.getUsers()
    }

 🔹Example:
 runTest {
 val api: UserApi = mock()
 val repository = UserRepository(api)

 whenever(api.getUsers())
 .thenThrow(RuntimeException("Network error"))

 val exception = assertFailsWith<RuntimeException> {
 repository.getUsers()
 }
 assertEquals("Network error", exception.message)
 }

 */




