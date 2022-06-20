























package dev.anand.synchronossweatherapp

import dev.anand.synchronossweatherapp.data.api.CurrentWeatherService
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    suspend fun weather_api() {
        val weatherService: CurrentWeatherService =
            CurrentWeatherService.create()

        try {
           val response= weatherService.getWeather(10.76181,78.7088)

            assertEquals(response.name,"Trichy")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}