package dev.anand.synchronossweatherapp.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.anand.synchronossweatherapp.data.api.CurrentWeatherService
import dev.anand.synchronossweatherapp.data.api.model.asDatabaseModel
import dev.anand.synchronossweatherapp.data.db.WeatherDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
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
            database.weatherInfoDao().getAll().collectLatest {
                Timber.d("size of db = ${it.size}")
                if(it.isEmpty()){
                    val weather = CurrentWeatherService.create().getWeather(lat!!.toDouble(),lng!!.toDouble())
                    database.weatherInfoDao().insertAll(listOf(weather.asDatabaseModel()))
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