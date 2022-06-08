package dev.anand.synchronossweatherapp.repository

import androidx.annotation.WorkerThread
import dev.anand.synchronossweatherapp.data.db.dao.CurrentWeatherDAO
import dev.anand.synchronossweatherapp.data.model.CurrentWeatherForecastResponse
import dev.anand.synchronossweatherapp.network.CurrentWeatherService
import timber.log.Timber
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val weatherService: CurrentWeatherService,
    private val weatherDAO: CurrentWeatherDAO
) {
    init {
        Timber.d("AppRepository","Inject app repo")
    }

    @WorkerThread
    suspend fun getWeather(latitude:Double,longitude: Double
    ): CurrentWeatherForecastResponse {
        //val weather = weatherDAO.getWeather().value
        //if(weather == null){
            val  we = weatherService.getForecastByG(latitude, longitude)
            Timber.d("WEATHER ${we.toString()}")
            return we

       // }
    }
}