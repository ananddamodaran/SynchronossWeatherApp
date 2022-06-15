package dev.anand.synchronossweatherapp.data.api

import dev.anand.synchronossweatherapp.data.api.model.WeatherResponse
import dev.anand.synchronossweatherapp.data.model.CurrentWeatherForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherService {
    @GET("forecast")
    suspend fun getForecastByG(
        @Query("lat")
        lat: Double,
        @Query("lon")
        long: Double,
    ): CurrentWeatherForecastResponse

    @GET("weather?units=metric")
    suspend fun getWeather(
        @Query("lat")
        lat: Double,
        @Query("lon")
        long: Double,
    ): WeatherResponse

}