package com.example.systemdesign.design.chatApplication.presentation.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel


@Composable
fun ChatScreen(conversationId: Int){
    val viewModel: ChatViewModel = hiltViewModel()

    val messages by viewModel.getAllMessage(conversationId).collectAsState()

    var text by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(
                items = messages,
                key = { message ->
                    message.clientMsgId
                }
            ) { message ->

                MessageItem(
                    message = message
                )
            }
        }
    }

        Row {

            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                modifier = Modifier.weight(1f)
            )


            Button(
                onClick = {
                    viewModel.sendMessage(
                        conversationId = conversationId,
                        senderId = 501,
                        receiverId = -1,
                        text = text
                    )

                    text = ""
                }
            ) {

                Text("Send")
            }
        }
    }
