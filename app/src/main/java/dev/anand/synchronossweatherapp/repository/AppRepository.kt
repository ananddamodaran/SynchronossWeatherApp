package dev.anand.synchronossweatherapp.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import dev.anand.synchronossweatherapp.data.api.CurrentWeatherService
import dev.anand.synchronossweatherapp.data.api.model.asDatabaseModel
import dev.anand.synchronossweatherapp.data.db.WeatherInfoDao
import dev.anand.synchronossweatherapp.data.db.asDomainModel
import dev.anand.synchronossweatherapp.domain.CurrentWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val weatherService: CurrentWeatherService,
    private val weatherDAO: WeatherInfoDao
) {
    val weather: LiveData<CurrentWeather> =
        Transformations.map(weatherDAO.getWeather().asLiveData()) {
            it?.asDomainModel()
        }


    @WorkerThread
    suspend fun getWeatherFlow(latitude: Double, longitude: Double) {

        val weather = weatherService.getWeather(latitude, longitude)
        Timber.d("WEATHER ${weather.toString()}")
        val we = weather.asDatabaseModel()
        withContext(Dispatchers.IO) {
            weatherDAO.insertAll(listOf(we))
        }

    }
}