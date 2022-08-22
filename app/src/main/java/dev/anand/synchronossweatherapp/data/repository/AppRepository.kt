package dev.anand.synchronossweatherapp.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import dev.anand.synchronossweatherapp.data.local.WeatherInfoDao
import dev.anand.synchronossweatherapp.data.local.WeatherInfoEntity
import dev.anand.synchronossweatherapp.data.mapper.toWeatherInfo
import dev.anand.synchronossweatherapp.data.mapper.toWeatherInfoEntity
import dev.anand.synchronossweatherapp.data.remote.OpenWeatherService
import dev.anand.synchronossweatherapp.domain.model.WeatherInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
  private val weatherApi: OpenWeatherService,
  private val weatherDAO: WeatherInfoDao,

  ) {
  val weather: LiveData<WeatherInfo> =
    Transformations.map(weatherDAO.getAll().asLiveData()) {
      it?.toWeatherInfo()
    }


  @WorkerThread
  suspend fun getWeatherFlow(latitude: Double, longitude: Double) {
    Timber.d("fetchFrom API $latitude - $longitude")
    withContext(Dispatchers.IO) {
      val weather = weatherApi.getWeather(latitude, longitude)
      val we = mutableListOf<WeatherInfoEntity>()
      weather?.toWeatherInfoEntity()?.let { we.add(it) }

      weatherDAO.clear()
      weatherDAO.insertAll(we)
    }

  }

}