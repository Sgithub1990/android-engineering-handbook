package com.example.systemdesign.design.chatApplication.presentation.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.systemdesign.systemdesign.chatApplication.data.local.MessageEntity
import com.example.systemdesign.systemdesign.chatApplication.data.local.MessageStatus

@Composable
fun MessageItem(
    message: MessageEntity
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Text(
            text = message.msg
        )

        Text(
            text = MessageStatus.valueOf( message.status.toString()).name,
            style = MaterialTheme.typography.labelSmall
        )
    }
}