package dev.anand.synchronossweatherapp.data.repository

import androidx.annotation.WorkerThread
import dev.anand.synchronossweatherapp.data.local.WeatherInfoDao
import dev.anand.synchronossweatherapp.data.local.WeatherInfoEntity
import dev.anand.synchronossweatherapp.data.mapper.toWeatherInfo
import dev.anand.synchronossweatherapp.data.mapper.toWeatherInfoEntity
import dev.anand.synchronossweatherapp.data.remote.OpenWeatherService
import dev.anand.synchronossweatherapp.domain.model.WeatherInfo
import dev.anand.synchronossweatherapp.domain.repository.SynchronossWeatherRepository
import dev.anand.synchronossweatherapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SynchronossWeatherRepositoryImpl @Inject constructor(
  private val weatherApi: OpenWeatherService,
  private val weatherDAO: WeatherInfoDao
) : SynchronossWeatherRepository {

  @WorkerThread
  override suspend fun getWeather(
    fetchFromRemote: Boolean,
    lat: Double,
    lng: Double
  ): Flow<Resource<List<WeatherInfo>>> = flow {
    emit(Resource.Loading(true))
    val localWeatherInfoList = weatherDAO.getWeatherList()
    emit(
      Resource.Success(
        data = localWeatherInfoList.map {
          it.toWeatherInfo()
        }
      )
    )
    val isDbEmpty = localWeatherInfoList.isEmpty()
    val shouldLoadFromCache = !isDbEmpty //&& !fetchFromRemote
    if (shouldLoadFromCache) {
      emit(Resource.Loading(false))
      return@flow
    }


    Timber.d("fetchFrom API $lat - $lng")
    try {
      val response = weatherApi.getWeather(lat, lng)
      val weatherEntityList = mutableListOf<WeatherInfoEntity>()
      response?.toWeatherInfoEntity()?.let { weatherEntityList.add(it) }
      weatherDAO.clear()
      weatherDAO.insertAll(weatherEntityList)
      emit(Resource.Success(
        data = weatherDAO
          .getWeatherList()
          .map { it.toWeatherInfo() }
      ))
      emit(Resource.Loading(false))
    } catch (ex: Exception) {
      emit(Resource.Error("Couldn't load data"))
      null
    }
  }

}