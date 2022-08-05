package dev.anand.synchronossweatherapp.data.remote

import dev.anand.synchronossweatherapp.BuildConfig
import dev.anand.synchronossweatherapp.data.remote.dto.WeatherInfoDto
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

interface OpenWeatherService {
    suspend fun getWeather(lat:Double,long:Double): WeatherInfoDto?
    companion object {
        fun createHttpClient(): OpenWeatherService {
            val client = HttpClient(Android) {
                engine {
                    connectTimeout = 30_000
                    socketTimeout = 30_000
                }

                install(Logging){
                    logger = CustomLogger()
                    level = LogLevel.ALL
                }

                install(JsonFeature) {
                    serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    })
                }


            }
            client.sendPipeline.intercept(HttpSendPipeline.State) {
                context.headers.append("AppVersion", BuildConfig.VERSION_NAME)
                context.parameter("appid",BuildConfig.WEATHER_APP_ID)
            }
            return OpenWeatherServiceImpl(client)
        }
    }
}