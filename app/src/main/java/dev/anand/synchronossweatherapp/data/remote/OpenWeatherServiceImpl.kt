package dev.anand.synchronossweatherapp.data.remote

import dev.anand.synchronossweatherapp.data.remote.dto.WeatherInfoDto
import dev.anand.synchronossweatherapp.util.Constants
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class OpenWeatherServiceImpl(private val client: HttpClient) : OpenWeatherService {
  override suspend fun getWeather(lat: Double, long: Double): WeatherInfoDto? {

    return try {
      client.get {
        url(Constants.GET_WEATHER)
        parameter("lat", lat)
        parameter("lon", long)

      }
    } catch (ex: RedirectResponseException) {
      // 3xx - responses
      println("Error: ${ex.response.status.description}")
      null
    } catch (ex: ClientRequestException) {
      // 4xx - responses
      println("Error: ${ex.response.status.description}")
      null

    } catch (ex: ServerResponseException) {
      // 5xx - response
      println("Error: ${ex.response.status.description}")
      null

    }
  }
}