package com.example.systemdesign.systemDesign.paymentflow.data.local.order

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.systemdesign.systemDesign.paymentflow.data.remote.dto.OrderResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(order: OrderResponse)

    @Query("select * from `order`")
     fun getAllOrder(): Flow<List<OrderResponse>>

}