package dev.anand.synchronossweatherapp.domain.repository

import dev.anand.synchronossweatherapp.domain.model.WeatherInfo
import dev.anand.synchronossweatherapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface SynchronossWeatherRepository {
    suspend fun getWeather(
        fetchFromRemote:Boolean,
        lat: Double, lng: Double): Flow<Resource<List<WeatherInfo>>>
}