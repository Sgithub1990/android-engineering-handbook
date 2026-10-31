package com.example.systemdesign.epam.testCase.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

@Composable
fun Login(){

}

class ComposeUITstCase {
    @get:Rule
    val composeRule = createComposeRule()

    @Composable
    @Test
    fun LoginTest(){
        composeRule.setContent(
            { Login() }
        )
        composeRule.onNodeWithText("email").performTextInput("shyam@Gmail.com")
        composeRule.onNodeWithText("login").performClick()
    }
}
