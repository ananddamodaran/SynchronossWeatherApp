package dev.anand.synchronossweatherapp.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.WorkerParameters
import dev.anand.synchronossweatherapp.data.api.CurrentWeatherService
import dev.anand.synchronossweatherapp.data.api.model.asDatabaseModel
import dev.anand.synchronossweatherapp.data.db.WeatherDatabase
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
        if(weatherInfo!=null) {
            Timber.d(weatherInfo.name)
           // val diff = Calendar.getInstance().timeInMillis - weatherInfo.dt * 1000
           // val hours: Int = (diff / (1000 * 60 * 60)).toInt()
            //Timber.d(diff.toString())
            val weather =
                CurrentWeatherService.create().getWeather(weatherInfo.lat, weatherInfo.lng)
            database.weatherInfoDao().clear()
            database.weatherInfoDao().insertAll(listOf(weather.asDatabaseModel()))

        }
        Result.success()



    }

    companion object {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    }
}