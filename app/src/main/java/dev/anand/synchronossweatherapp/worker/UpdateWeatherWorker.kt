package dev.anand.synchronossweatherapp.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dev.anand.synchronossweatherapp.data.db.CurrentWeatherEntity
import dev.anand.synchronossweatherapp.data.db.WeatherDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class UpdateWeatherWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {


    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {

        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val weatherType = object : TypeToken<List<CurrentWeatherEntity>>() {}.type
                        val weatherList: List<CurrentWeatherEntity> =
                            Gson().fromJson(jsonReader, weatherType)

                        val database = WeatherDatabase.getInstance(applicationContext)
                        database.weatherDao().insertAll(weatherList)

                        Result.success()
                    }
                }
            } else {
                Timber.tag(TAG).e("Error seeding database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Timber.tag(TAG).e(ex, "Error seeding database")
            Result.failure()
        }

    }


    companion object {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()


        private const val TAG = "WeatherDatabaseWorker"
        const val KEY_FILENAME = "WEATHER_DATA_FILENAME"


    }
}