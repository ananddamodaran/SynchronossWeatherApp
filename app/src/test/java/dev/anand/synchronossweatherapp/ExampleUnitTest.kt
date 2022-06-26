























package dev.anand.synchronossweatherapp

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import dev.anand.synchronossweatherapp.data.api.CurrentWeatherService
import dev.anand.synchronossweatherapp.data.api.model.Weather
import dev.anand.synchronossweatherapp.data.db.WeatherDatabase
import dev.anand.synchronossweatherapp.data.db.WeatherInfoDao
import dev.anand.synchronossweatherapp.repository.AppRepository
import dev.anand.synchronossweatherapp.viewmodel.HomeScreenViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

   // lateinit var viewModel : HomeScreenViewModel
   // lateinit var instrumentationContext: Context


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

   /* @Before
    fun setup(){
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        val api = CurrentWeatherService.create()
        val weatherDAO = WeatherDatabase.getInstance(instrumentationContext).weatherInfoDao()
        viewModel = HomeScreenViewModel(AppRepository(api,weatherDAO))
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

    @Test
    fun testFromViewModel(){
        viewModel.getWeatherFlow(10.76181,78.7088)
        assertEquals(viewModel.weather.value?.name , "Trichy")
    }
*/
    @Test
    fun testWeather(){
        val weather = Weather(description = "cloudy","10n",
        id=1, main = "Cloud")

        val expectedResult= Weather(description = "Rain","10n",
            id=1, main = "Cloud")

        assertEquals(weather,expectedResult)
    }
}