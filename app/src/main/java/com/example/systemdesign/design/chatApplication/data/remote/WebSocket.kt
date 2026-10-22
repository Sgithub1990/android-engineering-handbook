package com.example.systemdesign.design.chatApplication.data.remote

import androidx.compose.runtime.mutableStateOf
import com.example.systemdesign.design.chatApplication.data.local.MessageEntity
import com.example.systemdesign.design.chatApplication.data.local.MessageStatus
import com.example.systemdesign.design.chatApplication.domain.socket.SocketEvent
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import javax.inject.Inject
import javax.inject.Named

class WebSocket @Inject constructor(@Named("ChatHttpClient") private val client: OkHttpClient) {

    /*init web socket*/
   var webSocket : WebSocket?=null
    private val _events = MutableSharedFlow<com.example.systemdesign.design.chatApplication.domain.socket.SocketEvent>()
    val events: SharedFlow<com.example.systemdesign.design.chatApplication.domain.socket.SocketEvent> = _events.asSharedFlow()

    fun connect(){
        /**create request*/
        val request = Request.Builder().url("").build()

        /**create websocket*/
        webSocket = client.newWebSocket(request,object: WebSocketListener(){

            override fun onClosed(
                webSocket: WebSocket,
                code: Int,
                reason: String
            ) {
               _events.tryEmit(SocketEvent.Disconnect)
            }

            override fun onOpen(webSocket: WebSocket, response: Response) {
                _events.tryEmit(SocketEvent.Connect)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                handleMessage(text)
            }

            override fun onFailure(
                webSocket: WebSocket,
                t: Throwable,
                response: Response?
            ) {
                _events.tryEmit(SocketEvent.Error(t))
            }

            override fun onClosing(
                webSocket: WebSocket,
                code: Int,
                reason: String
            ) {
                super.onClosing(webSocket, code, reason)
                _events.tryEmit(SocketEvent.Disconnect)
            }
        })

    }

    fun disconnect(){
        webSocket = null;
    }

    fun handleMessage(msg: String){
        val msg = Gson().fromJson(msg, MessageEntity::class.java)

        when(MessageStatus.valueOf(msg.status.toString())){
            MessageStatus.SENT -> {
                _events.tryEmit(SocketEvent.MessageSent(msg.clientMsgId))
            }
            MessageStatus.DELIVERED -> {
                _events.tryEmit(SocketEvent.MessageDelivered(msg.clientMsgId))
            }
            MessageStatus.READ -> {
                _events.tryEmit(SocketEvent.MessageRead(msg.clientMsgId))

            }
            MessageStatus.FAILED -> {
                _events.tryEmit(SocketEvent.Error(Throwable("error",null)))

            }
            else -> {

            }
        }

    }


}