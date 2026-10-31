package com.example.systemdesign.epam.misllenious
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
🔥 design counter app and find recomposition
 */



@Composable
fun CounterApp(){
    var count by remember { mutableIntStateOf(0) }
    Column{
        Text("Count: $count")
        Button(onClick = {count++}) {
            Text("Increment")
        }
        UserProfileView()
    }
}
@Composable
fun UserProfileView() {
    Text("User Profile")
}
/**
🔸Insights
1- remember store the count state across recomposition
2- when button click -> compose observe state change and schedule recomposition for effected scope
3- UserProfile may skipped when compose decide its input does not change
 */

/**
🔥 Why doesn't this code update the UI correctly?
*/

@Composable
fun CounterScreen() {
    var count = 0

    Button(onClick = {
        count++
    }) {
        Text("Count: $count")
    }
}

/**
🔸Insights:
1- count is local normal variable not compose state
2- Compose does not observe changes to count, so recomposition is not triggered when it changes.
3- when recomposition happen it again initialize to 0
 */

/**
🔸Difference:
API	                          Recomposition	        Configuration change	           Process death
remember	                  Yes,	                No,	                               No
rememberSavable	              Yes,	                Yes,	                           Yes
ViewModel	                  Yes,	                Yes,	                           No
ViewModel+SavedStateHandle	  Yes,	                Yes	Small restorable state,        when Android restores saved state
 */


/**
🔥 SateHosting[Reusable component]
  Design a counter whose state is controlled by its parent.
 */

@Composable
fun Screen(){
    var count by remember { mutableIntStateOf(0) }
    Counter(count) {
        count++
    }
}

@Composable
fun Counter(count: Int,
            onCountChange:()->Unit){
  Column {
     Text("Count$count")
     Button(onClick = {onCountChange()}) {
         Text("Increase")
     }
  }
}

/**
🔥Why should you not call an API directly inside a composable body?
*/
@Composable
fun CallApi(){
    val viewModel = hiltViewModel<ComposeCodingPracticeViewModel>()
    val user = viewModel.user.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.callApi()
    }
}

/**
🔸Insights:
1- Every Recomposition trigger api call repeatedly which is waste of time, memory and resource
🔹Launch effect->
 1- provide coroutine scope and tied with Composable's lifecycle.
 2- It Restarts when key change
 3- Cancels its coroutine when it leaves the Composition.
 Ideally -> Api call define in viewModel init block
 */

/**
🔥You need to register a lifecycle observer when a composable enters composition and remove it when it leaves. How?
 */
@Composable
fun RemoveObserver(){
    val owner = LocalLifecycleOwner.current
    DisposableEffect(Unit) {
        val observer = LifecycleEventObserver{_,event->
          print("observe")
         }
        owner.lifecycle.addObserver(observer)
        onDispose {
          owner.lifecycle.removeObserver(observer)
        }
    }

}

/**
🔥When should you use SideEffect? give example
 */

@Composable
fun AddAnalyticalEvent(userName: String){
    SideEffect {
      print("analytical event: $userName")
    }
    Text("Hello: $userName")
}

/**
🔸Insights:
1- first print Hello userName
2- after successful recomposition sideEffect run and publish analytical event
 */

/**
🔸Very IMP:
API	                                     Primary use
LaunchedEffect(key)	                    Composition-scoped coroutine
DisposableEffect(key)	                Setup and cleanup
rememberUpdatedState(value)          	Read the latest value inside a long-running effect
SideEffect	                            Publish state after successful composition
produceState	                        Convert external or asynchronous data into Compose State
derivedStateOf	                        Avoid unnecessary updates when a derived value changes less often than its inputs
rememberCoroutineScope()	            Launch a coroutine from a UI event handler
 */


/**
🔥 Stability — why does Compose recompose unnecessarily?
 */

data class User1(
    val name: String
)

/**
🔸Insights Stable:
1- Stability -> Stability helps Compose decide whether composition happen or skip
2- User(val name: String) can be inferred as stable. If its inputs remain equal, Compose can skip recomposition,
3- Example: String, Int, immutable model of stable
 */

data class User2(
    val names: List<String>
)
/**
🔸Insights UnStable:
1-Calling names.add("C") does not automatically trigger recomposition. Prefer observable state collections such as mutableStateListOf
Example: List , var
 */


/**
🔸Very IMP:
Concept               	 Meaning
Stable	                 Compose can reason about whether inputs have changed
Immutable	             The object's observable contents cannot change after creation
val	                     Prevents reassignment of the reference; doesn't guarantee the referenced object is immutable
@Stable	                 A contract you make with Compose about observable changes
@Immutable	             A contract that the object's publicly observable properties do not change after construction
List<T>	                 A read-only interface, not proof that the underlying list is immutable
 */

/**
🔥Show a “Back to top” button only when a list has been scrolled.
 */

@Composable
fun ShowBackToTopCTAInList(users: List<UserProfile>){
    val listState  = rememberLazyListState()
     val showCta by remember { derivedStateOf {
        listState.firstVisibleItemIndex>0
    } }
     Column{
     LazyColumn(state = listState) {
         items<UserProfile>(items = users, key = { item: UserProfile -> item.userId }) { user ->
             Text(text = user.userName)
         }
     }
         if(showCta){
       Button(onClick = {}) {
        Text("Scroll To Top ")
       }
        }

    }

}

/**
🔸Insights derivedState [improve performance]:
1- Derived state used when result change less frequent than input state change
🔹Without Derived State:
 scroll change -> firstVisibleItemIndex changes -> FeedScreen can recompose -> showCta is recalculated on each recomposition, even when its Boolean value remains true
🔹With Derived State:
 scroll change -> derivedStateOf recalculates its derived value -> If the Boolean result remains true, Compose does not invalidate composables that depend only on this derived Boolean because its value hasn't changed.
 */

/**
🔸Insights Stable Key
 Stable key is used to preserve item identity when list change
 1- By default compose identify item by its position but if position change then may get wrong result
 2- with stable key compose identify item by its sable key

* */