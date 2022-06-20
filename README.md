### SynchronossWeatherApp

A weather app built with Jetpack Compose consuming [OpenWeather API](https://openweathermap.org/api)



[![Build Status - Github Actions][]][Build status]

[Build Status - Github Actions]: https://github.com/ananddamodaran/SynchronossWeatherApp/actions/workflows/ci.yml/badge.svg?branch=dev
[Build status]: https://github.com/ananddamodaran/SynchronossWeatherApp/actions

#### **Libraries**

**Kotlin** - Kotlin is a programming language that can run on JVM. Google has announced Kotlin as one of its officially supported programming languages in Android Studio; and the Android community is migrating at a pace from Java to Kotlin

**Jetpack components:**

**Jetpack Compose** - Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.

**Android KTX** - Android KTX is a set of Kotlin extensions that are included with Android Jetpack and other Android libraries. KTX extensions provide concise, idiomatic Kotlin to Jetpack, Android platform, and other APIs.
AndroidX - Major improvement to the original Android Support Library, which is no longer maintained.

**Lifecycle** - Lifecycle-aware components perform actions in response to a change in the lifecycle status of another component, such as activities and fragments. These components help you produce better-organized, and often lighter-weight code, that is easier to maintain.

**ViewModel** -The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.

**LiveData** - LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

**Kotlin Coroutines** - A concurrency design pattern that you can use on Android to simplify code that executes asynchronously.

**Retrofit** - Retrofit is a REST client for Java/ Kotlin and Android by Square inc under Apache 2.0 license. Its a simple network library that is used for network transactions. By using this library we can seamlessly capture JSON response from web service/web API.

**GSON** - JSON Parser,used to parse requests on the data layer for Entities and understands Kotlin non-nullable and default parameters.

**Kotlin Flow** - In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value.

**Dagger Hilt** - A dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.

**Logging Interceptor** - logs HTTP request and response data.

**Coil** - An image loading library for Android backed by Kotlin Coroutines.

**Timber** - A logger with a small, extensible API which provides utility on top of Android's normal Log class.

**CI/CD:**

**GitHub Actions** - GitHub Actions makes it easy to automate all your software workflows, now with world-class CI/CD. Build, test, and deploy your code right from GitHub. Make code reviews, branch management, and issue triaging work the way you want.

#### Application architecture

![mvvm-architecture](https://user-images.githubusercontent.com/29427904/174492802-ec28d4a9-e01f-471f-8486-183a772f535c.png)

