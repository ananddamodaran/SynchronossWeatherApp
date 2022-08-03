package dev.anand.synchronossweatherapp.data.remote

import io.ktor.client.features.logging.*
import timber.log.Timber

class CustomLogger: Logger {
    override fun log(message: String) {
        Timber.tag("SynchronossWeatherApp").d(message)
    }
}