package com.example.systemdesign.epam.misllenious

/**
🔥What is an ANR?
🔸Application Not Responding -> Main thread is blocked for too long so application does not respond user input
🔹Cause:
 Long running task like database Query , Network Operation , File I/O in main thread
🔹How to trace:
Crash Analytics , Android Logcat , android vitals [Google Play Console → Quality → Android vitals], profiler/perfetto
🔹Prevent:
1- Avoid Heavy work on the Main Thread
2- Use StrictMode
3- Use Coroutine for long running task
🔹Fix
Reproduce -> Analyse Impact -> Fix -> Regression -> Release -> Monitor

 */

/**
🔸Detect ANR:

🔹Problem:
fun loadProfile() {
   val result = runBlocking {
   repository.fetchProfile()
   }
   showProfile(result)
  }

📌Solution:  avoid using runBlocking bcz it block main thread so application not responding


🔹Problem:
fun calculate() {
  var sum = 0L
  for (i in 1..100_000_000) {
   sum += i
   }
  }

📌Solution: Long running loop in main thread cause ANR
 🔹fun calculate() {
   val sum = withCotext(Dispatcher.Default){
   var result = 0L
   for (i in 1..100_000_000) {
   result += i
   }
  result
  }

 */


/**
🔥What is an Memory leak?
A memory leak happens when an object is no longer needed but remains reachable, so the garbage collector cannot reclaim it.
🔹Cause:

Source	              Why it leaks	                                  Typical fix
Singleton	          Holds an Activity or View	                      Remove the reference
Handler/callback	  Delayed work retains a screen	                  Cancel callbacks or use lifecycle-aware work
Coroutine	          Work outlives its intended owner	              Use viewModelScope or lifecycleScope appropriately
Listener/observer	 Registration isn't removed	                      Unregister when the owner is finished

🔹How to trace:
 1- leak canary
 2- Memory Profiler [swallow object , retain object , leak ]
 */

/**
🔥What is MacroBenchmark ?
 MacroBench mark used to analyse app or flow performance like a real user
🔹It Provide
 1- StartTup time Matrix
 2- FrameTime Matrix

🔹what Check :
 1 - Time to App startUp
 2- Time to navigate from one Screen to Other
 3- Scroll time (jerk)
 4- UI performance

 */

/**
🔥What is Perfetto ?
Perfetto is App performance  detailed(bottom neck ) anlyse tool which Provide detailed analysis of what app is doing over time
without this we only know app is slow but with it we check
🔹what Check :
1-How much time take init SDK
2- How much time take Application onCreate
3- How much time take DB initialization
📌Internally MacroBench mark use Perfetto

* */


/**
🔥App startup performance
1-Cold StartUP
AppOpen -> Linux Process Create -> Application OnCreate -> Activity OnCreate -> Activity Start -> Activity Resume
🔹Example -> Activity Launch after force close

2-Warm StartUp
AppOpen -> Process Already Created -> Activity OnCreate -> Activity Start -> Activity Resume
🔹Example -> Activity Relaunch

3-Hot StartUp
AppOpen -> Process Already Created -> Activity Already in stack -> Activity ReStart  ->  Activity Resume
🔹Example -> Open after Home press


🔸How to trace:
1- Using Perfetto
2- Using MacroBench marck
3-TTID(Time to Initial Display) && TTFD (Time to Full Display)

🔸How increase StartUp Performance:
1- Use Differed Initialization of Non-critical work

🔹syntax:
  lifecycleScope.launch {
   delay(3000) ------------> differed
   syncServer()
   }

2- Use Lay Initialization
 val repository by lazy {
  Repository()
  }

🔸3 BaseLine Profile:
Base profile is list of important method code which initialize AOT by ART during app installation

🔹Without base Profile:

App Install -> process create -> ART analyse frequent method -> JIT compile  when app is running-> App sart slowly

🔹With base Profile:
App Install with BaseProfile ->  ART analyse frequent method  and compile during installation -> App starts fast from the first launch

🔹Steps For base Profile:
1- use baseLine profile Generator
2- use baselineRule.collect(package, includeInStartupProfile = true){

}
3- write unit test for important flow inside baseLineRule.collect
4- run generator it create baseline-prof.txt which is used by ART during app installation

 */


/**
🔥Process death and state restoration
Android can terminate an app process to reclaim memory.


Mechanism	              What it is for	                                      Survives process death?
remember	              Compose UI state	                                      No
ViewModel	              Screen state across configuration changes	              No, not by itself
rememberSaveable          Small, saveable Compose UI state	                      Often, through saved state restoration
SavedStateHandle	      Small screen state needed for recreation	              Often, when Android has saved the state
Room/persistent storage	  Durable application data	                              Yes
WorkManager	              Deferrable, persistent background work	              Work is persisted, subject to scheduling and system constraints

* */

/**
🔥ViewModel

ViewModelStoreOwner =                 the owner[Activity , Fragment ].
ViewModelStore =                      the container
ViewModel =                           the object inside the container.
NonConfigurationInstances =           a mechanism for retaining the store across configuration changes.
SavedStateRegistry =                  a mechanism for saving and restoring state, including across eligible process recreation.

🔸Process death :
ViewModel/Activity Destroy -> SaveState Handle Save value as Key-Value pair -> App Open Again -> Create Activity  ViewModel -> SaveSate Registry retrieve SaveState Handel -> SaveState Handel provide save value in vieModel

🔸Rotation:
ViewModel/Activity Destroy -> ViewModel Store saved in NonConfigurationInstances -> Activity Recrate ->NonConfigurationInstances retrieve viewModel store ->  viewModelStoreowner ask ViewModel -> ViewModel Store -> if ViewModel exist then provide else create new View Model

 */

