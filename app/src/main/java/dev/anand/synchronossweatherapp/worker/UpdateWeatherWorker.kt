package dev.anand.synchronossweatherapp.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.WorkerParameters
import timber.log.Timber

class UpdateWeatherWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {
    init {
        Timber.d("UpdateWeatherWorker")
    }

    override suspend fun doWork(): Result {

        return Result.success()
    }
    companion object{
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

    }
}