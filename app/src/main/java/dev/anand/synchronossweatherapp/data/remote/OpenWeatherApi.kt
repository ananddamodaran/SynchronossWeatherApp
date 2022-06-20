package dev.anand.synchronossweatherapp.data.remote

import dev.anand.synchronossweatherapp.BuildConfig
import dev.anand.synchronossweatherapp.data.remote.dto.WeatherInfoDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.LoggingEventListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("weather?units=metric")
    suspend fun getWeather(
        @Query("lat")
        lat: Double,
        @Query("lon")
        long: Double,
    ): WeatherInfoDto
    companion object {

        fun create(): OpenWeatherApi {

            val okHttpClient = OkHttpClient.Builder()
                .apply { if (BuildConfig.DEBUG) eventListenerFactory(LoggingEventListener.Factory()) }
                .addInterceptor(QueryParameterAddInterceptor())
                .addInterceptor(HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.WEATHER_APP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherApi::class.java)
        }
    }

}