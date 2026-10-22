package com.example.systemdesign.design.chatApplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Query("select * from message where conversationId=:conversationId order by timeStamp ASC")
    fun observeMsg(conversationId: Int): Flow<List<com.example.systemdesign.design.chatApplication.data.local.MessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(msg: com.example.systemdesign.design.chatApplication.data.local.MessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listMsg:List<com.example.systemdesign.design.chatApplication.data.local.MessageEntity>)

    @Query("select * from message where clientMsgId = :clientMsgId")
    suspend fun findMessageByClientMsgId(clientMsgId: Int): com.example.systemdesign.design.chatApplication.data.local.MessageEntity?

    @Query("update message set status=:status where clientMsgId=:clientMsgId")
    suspend fun updateStatus(clientMsgId: Int, status: com.example.systemdesign.design.chatApplication.data.local.MessageStatus)

    @Query("select * from message where status=:status order by timeStamp ASC")
    suspend fun getMessagesByStatus(status: com.example.systemdesign.design.chatApplication.data.local.MessageStatus): List<com.example.systemdesign.design.chatApplication.data.local.MessageEntity>

    @Query("update message set status = :status where sendId=:serverMessageId ")
    suspend fun updateStatusByServerId(serverMessageId: Int, status: com.example.systemdesign.design.chatApplication.data.local.MessageStatus)

}
