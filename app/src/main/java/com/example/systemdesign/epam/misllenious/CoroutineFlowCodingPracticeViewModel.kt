package com.example.systemdesign.epam.misllenious

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoroutineFlowCodingPracticeViewModel @Inject constructor(): ViewModel() {

      /**
      🔥 You need to fetch a user's profile and portfolio in parallel. Return the combined result. If either request fails, how should the other coroutine behave?
       */
    fun getUserData() {
        viewModelScope.launch {
            fetchProfileAndPortFolioParallel()
        }
    }
     suspend fun fetchProfileAndPortFolioParallel(): UserData {
        val profileDiffered = viewModelScope.async {
            fetchUserProfile()
        }
        val portFolioDiffered = viewModelScope.async {
            fetchUserPortFolio()
        }
        return UserData(
            profile = profileDiffered.await(),
            portfolio = portFolioDiffered.await()
        )
    }

    private suspend fun fetchUserProfile(): UserProfile {
        delay(1000)
        return UserProfile(100, "shyam", 8285809188)
    }

    private suspend fun fetchUserPortFolio(): UserPortfolio {
        delay(8000)
        return UserPortfolio(100, 10000, 100000, "TATA")
    }
     /**
     🔸Insights
     1- Sequential execution take 1+8           -> 9 sec
     2- concurrent execution take MaxOf(1,8)    -> 8 Sec
     3- coroutine scope does not return until all children is completed
     4-If one child failed it does not stop other child because viewModelScope internally use superVisorScope

     🔸Custom Scope :
     val Scope = CoroutineScope(supervisorJob()+Dispatch.IO)
     1-CoroutineScope — manages coroutine lifecycles.
     2-SupervisorJob() — allows child failures to be isolated from sibling coroutines.
     3-Dispatchers.IO — runs coroutine work on an I/O dispatcher.
     4-scope.cancel() — cancels the scope and its child coroutines.

     🔸Predefine Scope :
     Definition	        Who manages its lifetime?	     Typical use
     viewModelScope	    ViewModel	                     Android screen data
     coroutineScope {}	Calling coroutine	             Structured concurrency
     supervisorScope {}	Calling coroutine	             Independent child failures

     🔸Cancellation :
     🔹with SuperVisor
     1- one child failed with normal Exception               Other child get Executed
     2-one child cancel Execution                            Other child get Executed
     3- parent failed                                        All child failed
     🔹without SuperVisor
     1-one child failed with normal Exception               Other child  and parent failed
     2-one child cancel Execution                           Other child get Executed
     3- parent failed                                       All child failed
      */

    /**
    🔥A user starts downloading a file and then leaves the screen. How do you cancel the coroutine and release resources?
    */

    suspend fun downloadFile(){
        try {
            delay(1000)
            print("downloading")

        }catch (e: CancellationException){
            throw (e)
        }finally {
          print("release resource")
        }
    }

    /**
     🔸Insights
     1- don't handle CancellationException as normal cancellation silently ignore it. Let cancellation propagate.
     2- in finally release resource
     */


    /**
    🔥How CPU extensive  loop work know about cancel
     */
    fun calculation(){
      val job =   viewModelScope.launch {
            for(i in 1..100000){
             ensureActive()
             print(i)
            }
        }
        job.cancel()
    }
    /**
    🔸Insights
    1- we don't have cancellation aware point in loop so even job cancel but loop does not know
    2- we use ensureActive() to check cancellation inside loop to avoid work after cancel
     */


    /**
     🔥Given a stream of numbers, emit the squares of only even numbers.
     */

    fun numberFlow(): Flow<Int> = flow {
        for(i in 1..20){
            emit(i)
        }
    }
    suspend fun getSquireOfEvenNum(){
      numberFlow().filter {
        it % 2 == 0
      } .map { it*it }.collect {
          print(it)
      }
    }

    /**
    🔥How do you prevent the old search from continuing when a new query arrives?
     */

    val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
    fun searchQuery(){
        searchText.debounce(1000)
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .distinctUntilChanged()
            .flatMapLatest {
                searchQuery(it)
            }
    }
    fun searchQuery(query: String):Flow<List<String>>{
        return flowOf()
    }

    /**
    🔸Insights
    1- debounce -> wait for 1000 ms for next query emmit if before 1000 ms other query thet it reset
    2- map is used for trim query
    3- filter is used for validation
    4- distinctUntilChanged() avoid duplicate emit
    5- flatmapLatest cancel previous inner flow [cancel previous API]
     */


    /**
     🔥How would you expose loading, success and error states to a Jetpack Compose screen?
     */
    sealed interface UIState{
        data object LOADING: UIState
        data class ERROR(val error: String): UIState
        data class SUCCESS(val result: List<String>): UIState
    }
    val _uiState = MutableStateFlow<UIState>(UIState.LOADING)
     val uiState = _uiState.asStateFlow()

    fun getUser(){
       viewModelScope.launch {
           _uiState.value = UIState.LOADING
           try {
               _uiState.value = UIState.SUCCESS(emptyList())
           }catch (e: CancellationException){
               throw(e)
           }catch (e: Exception){
               _uiState.value = UIState.ERROR(e.toString())
           }finally {

           }
       }
    }

    /**
    🔸Insights
    StateFlow versus SharedFlow
    StateFlow	                           SharedFlow
    Holds a current value	               Does not hold but Configurable replay
    Requires an initial value	           No initial value required
    Conflates equal consecutive values	   Emission behavior depends on configuration
    Best for current UI state	           Useful for broadcast Event
     */

    /**
    🔥An API sometimes fails because of a temporary network problem. Retry up to three times with exponential backoff, but never retry cancellation.
     */





}