package dev.anand.synchronossweatherapp.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.anand.synchronossweatherapp.data.local.WeatherDatabase
import dev.anand.synchronossweatherapp.data.mapper.toWeatherInfoEntity
import dev.anand.synchronossweatherapp.data.remote.OpenWeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class UpdateWeatherWorker(val context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {


    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {

        try {
            val lat = inputData.getString("lat")
            val lng = inputData.getString("lng")
            Timber.d("$lat - $lng")
            val database = WeatherDatabase.getInstance(applicationContext)
            val weatherInfo = database.weatherInfoDao().getWeather()
            Timber.d("size of db = ${weatherInfo}")
            if (weatherInfo == null) {
               // val weather = OpenWeatherApi.create().getWeather(lat!!.toDouble(), lng!!.toDouble())
                val weather = OpenWeatherService.createHttpClient().getWeather(lat!!.toDouble(), lng!!.toDouble())
                if (weather != null) {
                    database.weatherInfoDao().insertAll(listOf(weather.toWeatherInfoEntity()))
                }

            }


            Result.success()

        } catch (ex: Exception) {
            Timber.tag(TAG).e(ex, "Error seeding database")
            Result.failure()
        }

    }


    companion object {
        private const val TAG = "WeatherDatabaseWorker"

    }
}