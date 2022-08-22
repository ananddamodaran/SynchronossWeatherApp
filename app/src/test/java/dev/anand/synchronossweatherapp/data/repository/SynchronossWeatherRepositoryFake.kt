package dev.anand.synchronossweatherapp.data.repository

import dev.anand.synchronossweatherapp.domain.model.WeatherInfo
import dev.anand.synchronossweatherapp.domain.repository.SynchronossWeatherRepository
import dev.anand.synchronossweatherapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SynchronossWeatherRepositoryFake : SynchronossWeatherRepository {
  var weatherInfoToReturn = WeatherInfo()

  override suspend fun getWeather(
    fetchFromRemote: Boolean,
    lat: Double,
    lng: Double
  ): Flow<Resource<List<WeatherInfo>>> = flow {
    emit(Resource.Success(listOf(weatherInfoToReturn)))

  }
}