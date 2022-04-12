# News App

News App is a simple project to study and play with some android components, architecture and tools for Android development.

## Tech Stack
This project is built following MVVM architecture. Supports day/night theme and different screen sizes and orientation change.

### Functionality
The app is composed of 2 main screens.

#### NewsListFragment
This fragment displays list of news using recycler view in descending order by article's timestamp.
The fragment request data from view model, view model then request repository to provide the news list. Repository fetches data from api and provides back to view model. View model then publishes the response to live data. View gets the update by observing different live data variables.

#### NewsDetailsFragment
This fragment displays the details of a selected news item in web view.

## Development setup
You  require the latest Android Studio Bumblebee(stable channel) to be able to build the app.

### Libraries

- Application entirely written in [Kotlin](https://kotlinlang.org)
- Asynchronous processing using [Coroutines](https://kotlin.github.io/kotlinx.coroutines/)
- Uses [Hilt](https://developer.android.com/jetpack/androidx/releases/hilt) for dependency injection.
-   REST api communication using [Retrofit](http://square.github.io/retrofit)
-  Uses [Glide](https://github.com/bumptech/glide) for image loading
-  [Timber](https://github.com/JakeWharton/timber) for logging

### API Endpoint

Api: (https://bruce-v2-mob.fairfaxmedia.com.au/1/coding_test/13ZZQX/full)

Note: You need to create `~/debug.properties` and `~/release.properties`  file to set the api endpoints in both of the files as
 baseUrl=https://bruce-v2-mob.fairfaxmedia.com.au/

 We are accessing the api endpoints using these files.
