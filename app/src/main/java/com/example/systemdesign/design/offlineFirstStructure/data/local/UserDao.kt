package com.example.systemdesign.design.offlineFirstStructure.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("select * from user")
    fun getUsers(): Flow<List<com.example.systemdesign.design.offlineFirstStructure.data.local.UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertUsers(user: List<com.example.systemdesign.design.offlineFirstStructure.data.local.UserEntity>)

}