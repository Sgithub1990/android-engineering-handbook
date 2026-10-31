package com.example.systemdesign.epam.misllenious


/**
 🔥why use MVVM
  🔸because MVVM is used to separate business logic from UI  and make application easier maintain and test
  1-model -> response model
  2-view -> UI
  3-viewModel -> Viewmodel
  4-data -> api , repository , database
 */


/**
🔥MVVM vs Clean Architecture

Feature	                      MVVM	                                  Clean Architecture
Main purpose	             Separate UI responsibilities	          Separate the whole application into layers
Main components	             View, ViewModel, Model	                  Presentation, Domain, Data
Use cases	                 Optional	                              Typically used for business operations
Repository	                 Commonly used	                          Repository interface in Domain; implementation in Data
Best for	                 Simple to medium apps	                  Complex, scalable applications
 */

/**
🔥why use UseCases
🔸Answer
 1- encapsulate business logic
 2-can coordinate to multiple repository
 3-make business logic separate , easy to Test
* */

/**
🔥why use Repository contract
🔸Answer
 repository contract used to make domain layer independent of datalayer
🔹without repository interface
 viewModel know the implementation of data layer
 * */

/**
🔥what is dependency inversion
🔸High level code should not depend on low level code implementation Both should depend on abstraction (interface) allowing implementations to change without affecting business logic.
🔹Without Dependency Inversion
 viewModel is directly depend on repositoryImp
 high level -> Viewmodel
 lowLevel -> repositoryImp
🔹With Dependency Inversion
ViewModel depends on UserRepository, not UserrepositoryImp.

* */


/**
🔥why dependency inversion useful
🔸Answer
1-Create an interface (UserRepository).
2-Make the ViewModel depend on that interface.
3-Pass a fake implementation during testing and a real implementation in production.

 * */

/**
🔥what is scope in hilt
🔸Scope define how long dependency instance live
Scope	                    Instance lifetime
@Singleton	                While the app's SingletonComponent exists
@ActivityRetainedScoped	    Across Activity recreation, until the retained component is cleared
@ActivityScoped	            For one Activity instance
@ViewModelScoped	        For one ViewModel's component
@FragmentScoped	            For one Fragment instance
* */

/**
🔥what is scope in hilt
🔸it tells hilt how to provide dependencies object when it is not created by own
* */

/**
🔥some important hilt Annotation
Annotation	               Purpose
@Module	                   Groups dependency-provision instructions for Hilt.
@InstallIn	               Specifies which Hilt component owns the module.
@Binds	                   Maps an implementation to an interface.
@Provides	               create an object, especially when you cannot use @Inject constructor in that class.[Room, retrofit,API]
@Singleton	               Reuses the same instance within that component.
* */


/**
🔥Modular architecture:
 splitting large application into small independent module
 1- Module structure devid whole app in separate gradle file
 2- every module can contain  presentation, business logic, and data access.

🔸Benefit:
 1- Maintainbility
 2- parallel development
 3- speedup build time
 4-Testing separately

🔸structure:
1-App
2 -feature
A- Login feature
B-Portfolio feature
3-Core
Retrofit,OkHTTP, API , CommonData

🔸 How to define module in gradle
🔹In settings.gradle:
 include(":App")
 include(":feature")
 include(":core")

🔸How does the app  use feature module?
🔹In app/build.gradle:
dependencies {
implementation(project(":feature:login"))
}
📌Now the app module depends on the portfolio module. but portfolio should not depend on app to avoid cyclic dependency
📌App module can directly access data exposed by a feature module. ✅
📌feature module does not access data of app module directly

🔸How does the feature  use network module?
In feature/login/build.gradle.kts:
dependencies {
implementation(project(":core:network"))
}

🔸how to communicate feature module with each other
 Feature module does not communicate with each other because they know each other implementation

1🔹App-level navigation / callbacks

  feature:portfolio --------------------------->app ------------------------> feature:stock
                    onOpenStocks(stockId)           navigates with stockId

📌 Portfolio define callback and app module implement this


2🔹via shareModule

:feature:portfolio ──┐
                     ├──> :core:auth
:feature:insurance ──┘

📌feature/portfolio/build.gradle.kts and feature/insurance/build.gradle.kts
dependencies {
implementation(project(":core:auth"))
}
📌Now both feature module can access core module


🔹what if Feature A needs to send new data to Feature B?
 we can define StateFlow in share Module


3🔹Shared state / observable data
both module can observe shared state

 */




