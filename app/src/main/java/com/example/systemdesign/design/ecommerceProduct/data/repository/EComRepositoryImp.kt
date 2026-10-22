package com.example.systemdesign.design.ecommerceProduct.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.systemdesign.design.ecommerceProduct.data.local.CartEntity
import com.example.systemdesign.design.ecommerceProduct.data.local.EComDataBase
import com.example.systemdesign.design.ecommerceProduct.data.local.EComProductEntity
import com.example.systemdesign.design.ecommerceProduct.data.remote.EComApiService
import com.example.systemdesign.design.ecommerceProduct.domain.repository.EComRepository
import com.example.systemdesign.design.ecommerceProduct.remotemediator.EComRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EComRepositoryImp @Inject constructor(val eComDataBase: EComDataBase,val apiService: EComApiService): EComRepository {

    override  fun getCartList(): Flow<List<CartEntity>> {
        return eComDataBase.eComDao().getCatItems()
    }

    @OptIn(ExperimentalPagingApi::class)
    override  fun getPager(): Flow<PagingData<EComProductEntity>> {
        return Pager(
                config = PagingConfig(pageSize = 10, maxSize = 20 ,prefetchDistance = 5),
                remoteMediator = EComRemoteMediator(apiService = apiService, eComDataBase = eComDataBase),
                pagingSourceFactory = {
                    eComDataBase.eComDao().getProducts()
                }
            ).flow


    }

    override suspend fun removeCartItem(id: Int) {
       eComDataBase.eComDao().removeFromCart(id)
    }

    override suspend fun insertIntoCart(product: EComProductEntity?) {
        val cartEntity = CartEntity(id = product!!.id,
            title = product.title,
            description = product.description,
            category = product.category,
            thumbnail = product.thumbnail)
       eComDataBase.eComDao().insertCart(cartEntity)
    }

    override suspend fun searchApi(searchText: String) {
        apiService.searchApi(searchText)
    }


}