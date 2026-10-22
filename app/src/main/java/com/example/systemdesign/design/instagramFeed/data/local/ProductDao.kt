package com.example.systemdesign.design.instagramFeed.data.local
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProduct(list: List<ProductEntity>)

    @Query("select * from productEntity")
    fun getAllProduct(): PagingSource<Int, ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllKeys(keys: List<RemoteKeyEntity>)

    @Query("select * from product_key where id=:id")
   suspend fun getKeyById(id: Int): RemoteKeyEntity


    @Query("DELETE from productEntity")
    fun removeAllProducts()

    @Query("DELETE from product_key")
    fun removeAllRemoteKeys()

    @Query("UPDATE productEntity SET isLiked = NOT isLiked WHERE id = :productId")
    fun updateLike(productId: Int)

    @Query("UPDATE productEntity SET likeSyncStatus = :likeSyncStatus WHERE id = :productId")
    fun updateLikeStatus(productId: Int,likeSyncStatus: Int)



}