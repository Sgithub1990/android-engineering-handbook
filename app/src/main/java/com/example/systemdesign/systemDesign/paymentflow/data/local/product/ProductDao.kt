package com.example.systemdesign.systemDesign.paymentflow.data.local.product

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(products: List<ProductEntity>)


    @Query("select * from product")
    fun getAllProduct(): Flow<List<ProductEntity>>

}