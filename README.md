# RoomApp
## About
### Objective:-
RoomApp is just a simple android app project for learning purpose. The idea was to demonstrate some core concepts used in today's modern Android development using Android Jetpack.
The major ones being:-
1. Kotlin co-routines
2. ViewModel and LiveData
3. Navigation Components
4. Room SQLite
5. MVVM architecture
6. Dependency Injection using [Dagger2](https://developer.android.com/training/dependency-injection/dagger-android) and [Dagger-Hilt](https://dagger.dev/hilt/).

**(Note: Dependency injection is NOT DONE in the _master_ branch. While the _dagger2_ and the _dagger-hilt_ branches contain code with dependency injection implemented using the libraries with the corresponding branch names.)**
 
### Basic app functionalities:-
For the sake of simplicity, this app contains the following functionalities:-
1. User can simply add,remove and edit random names. 
2. User can perform searches on the fetched names list.
3. When the user enters the query, the search can be performed in the following two ways:-
     - The search will happen on the already generated (RecyclerView's) list (ArrayList)
     - The search will happen directly on the database level (Room SQLite)
## Simple Walkthrough
https://user-images.githubusercontent.com/70072437/121355087-f9c1fb80-c948-11eb-8c85-cb1464b03bea.mp4
