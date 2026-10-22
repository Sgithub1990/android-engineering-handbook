package com.example.systemdesign.design.ecommerceProduct.data.local
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EComProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertProducts(products: List<EComProductEntity>)

   @Query("select * from eComProduct")
    fun getProducts(): PagingSource<Int, EComProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(keys: List<ERemoteKey>)

    @Query("select * from eremote where id=:productId")
    suspend fun getKeyById(productId: Int): ERemoteKey?
    @Query("DELETE from eComProduct")
   suspend fun deleteProducts()

    @Query("DELETE from eremote")
   suspend fun deleteRemoteKey()


   /*cart */

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertCart(cartEntity: CartEntity)

    @Query("select * from cart")
     fun getCatItems(): Flow<List<CartEntity>>

    @Query("DELETE  from cart where id=:id")
   suspend fun removeFromCart(id: Int)

}