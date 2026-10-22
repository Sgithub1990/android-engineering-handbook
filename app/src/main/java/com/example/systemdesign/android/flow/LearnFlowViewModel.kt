package com.example.systemdesign.android.flow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class LearnFlowViewModel: ViewModel() {


    fun channel(){
        val channel = Channel<Int>(capacity = 20)
        val coroutineProducer = viewModelScope.launch {
            channel.send(1);
            channel.send(2)
            channel.close()
        }
        val coroutineConsumer = viewModelScope.launch {
            for(item in channel){
            print("$item")
            }
        }
    }


    fun flow(){
        viewModelScope.launch {
            flowOf(1,2,3,4).collect {
                print("$it")
            }
        }
    }

    fun map(){
        viewModelScope.launch {
            flowOf(1,2,3,4).map { it.div(2) }
                .collect {
                print("$it")
            }
        }
    }

    fun filter(){
        viewModelScope.launch {
            flowOf(1,2,3,4).filter { it%2==0}
                .collect {
                print("$it")
            }
        }
    }

    fun take(){
        viewModelScope.launch {
            flowOf(1,2,3,4).take(2)
                .collect {
                print("$it")
            }
        }
    }

    fun zip(){
        viewModelScope.launch {
           val flow1=  flowOf(1,2,3,4,5,6)
           val flow2 = flowOf(1,2,3,4)
           viewModelScope.launch {
               flow1.zip(flow2,{a,b-> "$a$b" })
                   .collect {
                    print(it)
                  }
           }
        }

        /**  OutPut: 11,22,33,44  */
    }


    fun combine(){
        viewModelScope.launch {
           val flow1 =  flowOf(1,2,3,4,5,5)
           val flow2 = flowOf(1,2,3,4)
               flow1.combine(flow2,{a,b-> "$a$b"})
                   .collect {
                       print(it)
                   }

            /**  OutPut: 11,22,33,54,64  */
        }
    }

    fun combineExample(){
        val _userName = MutableStateFlow("")
        val userName = _userName.asStateFlow()
        val _password = MutableStateFlow("")
        val password = _password.asStateFlow()

        val isValid = userName.combine(password,{
                userName,password -> userName.isNotBlank() && password.isNotEmpty()
        })
        viewModelScope.launch {
            isValid.collect {
                /* handle ui validation */
            }
        }
        fun onUserNameChange(value: String) {
            _userName.value = value
        }
        fun onPasswordChange(value: String) {
            _password.value = value
        }
    }

    fun debounce(){
        viewModelScope.launch {
            flowOf(1,2,3,4).debounce(1000).collect {

            }
        } }


    fun debounceExample(){
         val _inputText = MutableStateFlow("")
         val inputText = _inputText.asStateFlow()

        viewModelScope.launch {
            inputText.debounce(1000)
                .collect {
                    /*api call */
                }
        }

        fun setInputText(text: String){
            _inputText.value = text
        }

    }

    fun buffer(){
        viewModelScope.launch {
            flowOf(1,2,3,4,5).buffer(4).collect {

            }
        }
    }

    fun distinctUntilChange(){
        viewModelScope.launch {
            flowOf(1,2,3,4,5,5,6).distinctUntilChanged().collect {

            }
        }
        /* output : 1,2,3,4,5
        remove duplicate
        * */
    }

    fun collectAsLatest(){
        viewModelScope.launch {
            flowOf(1, 2, 3).collectLatest { value ->
                println("Start $value")
                delay(1000)
                println("Finish $value")
            }
        }
        /*
        Start 1
        Start 2
        Start 3
        Finish 3
        */
    }

    /*create new inner flow form old flow and cancel old flow*/
    fun flatMapLatest(){
        viewModelScope.launch {
            flowOf(1,2,3).flatMapLatest {
             flowOf(it%2)
            }.collect{
              //output
            }
        }
    }


    fun flowOn(){
      /*run in io thread*/
     val flow = flowOf(1,2,3,4,5).flowOn(Dispatchers.IO)

      /*collect in main thread */
      viewModelScope.launch(Dispatchers.Main) {
         flow.collect {

         }
     }
    }

    fun retry(){
        viewModelScope.launch {
            flowOf(1,2,3,4,5).retry(2000).collect {

            }
        }

    }

    fun retryWhen(){
        viewModelScope.launch {
            flowOf(1,2,3,4,5).retryWhen{cause,attempt->
             attempt<3
            }.collect {

            }

            }
        }

    fun conflate(){
        viewModelScope.launch {
            flowOf(1,2,3,4,5).conflate().collect {

            }
        }
    }


    fun getStateFlow(): StateFlow<Int?>{
        return flowOf(1,2,3,4,5).stateIn(viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null,
            )
    }


    fun update(){
        val _num = MutableStateFlow(0)
        val num = _num.asStateFlow()

        /*
        For concurrency we use update
        avoid  _num.value = 1
        */
        _num.update {
             it+1
        }
    }


    fun onStartOnCompletion(){
        viewModelScope.launch {
            val flow = flowOf(1,2,3,4,5).onStart {
                print("run before flow emit")
            }.onCompletion {

                print("run after flow collect finish")
            }.collect {

            }
        }




    }


    }







