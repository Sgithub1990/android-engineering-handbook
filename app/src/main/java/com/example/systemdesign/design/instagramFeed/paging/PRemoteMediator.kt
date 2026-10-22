package com.example.systemdesign.design.instagramFeed.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.systemdesign.design.instagramFeed.data.local.ProductDatabase
import com.example.systemdesign.design.instagramFeed.data.local.ProductEntity
import com.example.systemdesign.design.instagramFeed.data.local.RemoteKeyEntity
import com.example.systemdesign.design.instagramFeed.data.remote.ProductApiService
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PRemoteMediator @Inject constructor(val productDataBase: ProductDatabase,val apiService: ProductApiService) : RemoteMediator<Int, ProductEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductEntity>
    ): MediatorResult {
      val page =  when(loadType) {
            LoadType.REFRESH ->{
             val remoteKey = getRemoteKeyForCurrentPage(state)
              remoteKey?.next?.minus(1)?:0
            }
            LoadType.PREPEND -> {
                val remoteKey = getRemoteKeyForPrePage(state)
                remoteKey?.pre?: return MediatorResult.Success(endOfPaginationReached = remoteKey!=null)

            }
            LoadType.APPEND ->{
            val remoteKey = getRemoteKeyForNextPage(state)
              remoteKey?.next?: return MediatorResult.Success(endOfPaginationReached = remoteKey!=null)
            }
          }

        /** get data from api */
        val limit = state.config.pageSize
        val skip = limit.times(page)
        val result =  apiService.getProduct(limit=limit,skip=skip)
        val productList = result.products
        val endOfPaginationReached =  productList.isEmpty()

        /** update database */
        productDataBase.withTransaction {
            if(loadType== LoadType.REFRESH){
                productDataBase.productDao().removeAllProducts()
                productDataBase.productDao().removeAllRemoteKeys()
            }

           productDataBase.productDao().insertAllProduct(productList)

           val keys: List<RemoteKeyEntity> = productList.map {
                RemoteKeyEntity(it.id,
                   pre = if(page==0){null}else{page-1},
                   next = if(endOfPaginationReached){null}else{page+1})
               }

           productDataBase.productDao().insertAllKeys(keys)

        }
        return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
    }

    suspend fun getRemoteKeyForCurrentPage(state: PagingState<Int, ProductEntity>): RemoteKeyEntity?{
      val anchorPosition =  state.anchorPosition
       if(anchorPosition!=null){
        val closestPage = state.closestItemToPosition(anchorPosition)
         return productDataBase.productDao().getKeyById(closestPage!!.id)
      }
        return null;
    }

   suspend fun getRemoteKeyForNextPage(state: PagingState<Int, ProductEntity>): RemoteKeyEntity?{
        val lastPage = state.pages.lastOrNull()
        val lastItem = lastPage?.data?.lastOrNull()
        if(lastPage!=null && lastItem!=null){
            return productDataBase.productDao().getKeyById(lastItem.id)
        }
        return null

    }

    suspend fun getRemoteKeyForPrePage(state: PagingState<Int, ProductEntity>):RemoteKeyEntity?{
        val firstPage = state.pages.firstOrNull();
        val firstItem = firstPage?.data?.firstOrNull()
        if(firstPage!=null && firstItem!=null){
            return productDataBase.productDao().getKeyById(firstItem.id)
        }
        return null
    }



}