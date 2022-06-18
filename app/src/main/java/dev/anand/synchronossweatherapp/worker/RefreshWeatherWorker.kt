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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.withContext
import timber.log.Timber

class RefreshWeatherWorker(val context: Context, workerParams: WorkerParameters):CoroutineWorker(
    context,workerParams
) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO){

        Timber.d("RefreshWeatherWorker doWork")
        val database = WeatherDatabase.getInstance(applicationContext)

        database.weatherInfoDao().getWeather().catch {
         Timber.e(it.localizedMessage)
            Result.failure()
        }.collect{
           Timber.d("${it?.name}")
            if(it!=null){
                val weather = CurrentWeatherService.create().getWeather(it.lat,it.lng)
                database.weatherInfoDao().clear()
                database.weatherInfoDao().insertAll(listOf(weather.asDatabaseModel()))
            }


       }




        Result.success()

    }
    companion object{
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    }
}