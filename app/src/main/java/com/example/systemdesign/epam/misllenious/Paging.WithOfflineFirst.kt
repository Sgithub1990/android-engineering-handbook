package com.example.systemdesign.epam.misllenious

/**
🔥Define repository Structure

🔹Code Example:
 Class Repository(dao, remoteMediator)
 {
 val pager(
 config =  PageConfig(pageCount = 20, maxSize  = 100, prefetchDistance = 5)
 remoteMediator = remoteMediator
 pagingSourceFactory  = dao.getPagingSource()
 ).Flow
 }
 */



/**
🔥Define Room Dao Structure

🔹Code Example:
 interface PagingDao{
 fun getPagingSource():PagingSource<Int , UserEntity>
 }
 */




/**
🔥RemoteKey Entity Structure

 data class RemoteKey(
 val pageId:Long
 val nextPage:Long
 val prePage:Long
 )
 */




/**
🔥how to save remote key and user in db

🔹Code Example:
  database.withTransaction{
  if (loadType == LoadType.REFRESH) {
  remoteKeysDao.clear()
  productDao.clear()
  }
  dao.insertAllUser(userlist)
   val keyList = userlist.map{
   RemoteKeyEntity(it.id,
   pre = if(page==0){null}else{page-1},
   next = if(endOfPaginationReached){null}else{page+1})
   }
   }
   dao.insertAllRemoteKey(keyList)
   }

 */


/**
🔥how to get remote key for current , next and prePage
🔹Code Example:

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

* */



/**
🔥Define RemoteMediator
🔹Code Example:

 Class UseRemoteMediator(room , Api):RemoteMediator<Int, UserEntity>{

 @fun load(loadType:LoadType, pagingState:PagingState<Int, UserEntity>):MediatorResult{
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

 }
 }

 */

/**
🔥How to getPagingFlow in ViewModel
🔹Code Example:
 val data = repository.data.cacheIn(viewModelScope)
*/

/**
🔥How to getPagingItem in View
🔹Code Example:
 val pagingListItem = viewmodel.data.collectAsLazyPagingItem()

* */
