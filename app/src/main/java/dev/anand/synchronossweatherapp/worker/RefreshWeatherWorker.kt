package dev.anand.synchronossweatherapp.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.WorkerParameters
import dev.anand.synchronossweatherapp.data.local.WeatherDatabase
import dev.anand.synchronossweatherapp.data.mapper.toWeatherInfoEntity
import dev.anand.synchronossweatherapp.data.remote.OpenWeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


class RefreshWeatherWorker(val context: Context, workerParams: WorkerParameters) : CoroutineWorker(
    context, workerParams
) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {

        Timber.d("RefreshWeatherWorker doWork")
        val database = WeatherDatabase.getInstance(applicationContext)

        val weatherInfo = database.weatherInfoDao().getWeather()
        if (weatherInfo != null) {
            Timber.d(weatherInfo.name)
            // val diff = Calendar.getInstance().timeInMillis - weatherInfo.dt * 1000
            // val hours: Int = (diff / (1000 * 60 * 60)).toInt()
            //Timber.d(diff.toString())
            val weather =
                OpenWeatherService.createHttpClient().getWeather(weatherInfo.lat, weatherInfo.lng)
            database.weatherInfoDao().clear()
            if (weather != null) {
                database.weatherInfoDao().insertAll(listOf(weather.toWeatherInfoEntity()))
            }

        }
        Result.success()


    }

    companion object {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    }
}