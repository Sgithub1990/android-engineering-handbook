package com.example.systemdesign.design.ecommerceProduct.remotemediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.systemdesign.design.ecommerceProduct.data.local.EComDataBase
import com.example.systemdesign.design.ecommerceProduct.data.local.EComProductEntity
import com.example.systemdesign.design.ecommerceProduct.data.local.ERemoteKey
import com.example.systemdesign.design.ecommerceProduct.data.remote.EComApiService
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class EComRemoteMediator @Inject constructor(val apiService: EComApiService,val eComDataBase: EComDataBase): RemoteMediator<Int, EComProductEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EComProductEntity>
    ): MediatorResult {

        val page = when(loadType) {
            LoadType.REFRESH -> {
                getCurrentPageRemoteKey(state)?.next?.minus(1)?:0
            }
            LoadType.PREPEND -> {
                val remoteKey = getPreviousPageRemoteKey(state)
                 getPreviousPageRemoteKey(state)?.pre?: return MediatorResult.Success(endOfPaginationReached = remoteKey!=null)

            }
            LoadType.APPEND -> {
                val remoteKey = getPreviousPageRemoteKey(state)
                getNextPageRemoteKey(state)?.next?: return MediatorResult.Success(endOfPaginationReached = remoteKey!=null)

            }
        }

        /*handle Request*/
         val limit = state.config.pageSize
         val skip = limit.times(page)
        val response =  apiService.getAllProductFromRemote(limit,skip)
        val listProduct = response.products
         val endOfPaging = listProduct.isNullOrEmpty()

        /*handle database*/

        eComDataBase.withTransaction {
            if(loadType== LoadType.REFRESH){
                eComDataBase.eComDao().deleteProducts()
                eComDataBase.eComDao().deleteRemoteKey()
            }

            eComDataBase.eComDao().insertProducts(listProduct)
            val keyList = listProduct?.map {
                ERemoteKey(
                    id = it.id,
                    pre = if(page==0){null}else{page-1},
                    next = if(endOfPaging){null}else{page+1}
                )
            }
            eComDataBase.eComDao().insertRemoteKey(keyList!!)
        }


        return MediatorResult.Success(endOfPaginationReached = endOfPaging)

    }



   suspend fun getCurrentPageRemoteKey(state: PagingState<Int, EComProductEntity>): ERemoteKey?{
        val anchorPosition = state.anchorPosition
        if(anchorPosition!=null){
            val closestItem = state.closestItemToPosition(anchorPosition)
            if(closestItem!=null){
             return eComDataBase.eComDao().getKeyById(closestItem.id)
            }
        }
       return null;

   }


    suspend fun getPreviousPageRemoteKey(state: PagingState<Int, EComProductEntity>):ERemoteKey?{
        val firstPage = state.pages.firstOrNull()
        if(firstPage!=null){
            val firstItem = firstPage.firstOrNull();
            if(firstItem!=null){
                return eComDataBase.eComDao().getKeyById(firstItem.id)
            }else{
                return null
            }
        }
        return null;
    }

    suspend fun getNextPageRemoteKey(state: PagingState<Int, EComProductEntity>):ERemoteKey?{
        val lastPage = state.pages.lastOrNull()
        if(lastPage!=null){
            val lastPageItem = lastPage.lastOrNull()
            if(lastPageItem!=null){
                return eComDataBase.eComDao().getKeyById(lastPageItem.id)
            }else{
                return null
            }
        }
        return null;
    }


}