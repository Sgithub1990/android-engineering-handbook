package com.example.systemdesign.design.chatApplication.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.systemdesign.design.chatApplication.data.local.MessageEntity
import com.example.systemdesign.design.chatApplication.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(val repository: ChatRepository): ViewModel() {

    init {
        repository.connectSocket()

    }

    fun getAllMessage(conversationId: Int): StateFlow<List<MessageEntity>>{
       return repository.observeMsg(conversationId).stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
            emptyList()
        )
    }

    fun sendMessage(conversationId: Int,
                     senderId: Int,
                     receiverId: Int,
                     text: String)

    {
        viewModelScope.launch {
            repository.senMsg(conversationId,senderId,receiverId,text)
        }
    }




}