package com.example.systemdesign.design.notificationSystem.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.systemdesign.design.notificationSystem.domain.Token


@Dao
interface TokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend  fun saveToken(token: com.example.systemdesign.design.notificationSystem.domain.Token)

  @Query("select * from token limit 1")
    suspend fun getToken(): com.example.systemdesign.design.notificationSystem.domain.Token?


    @Query("DELETE FROM token")
    suspend fun delete()
}

