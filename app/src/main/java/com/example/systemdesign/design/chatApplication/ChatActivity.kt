package com.example.systemdesign.design.chatApplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.systemdesign.design.chatApplication.presentation.chat.ChatScreen

class ChatActivity : ComponentActivity() {

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        setContent {
            MaterialTheme {
                ChatScreen(
                    2
                )

            }
        }
    }
}