package dev.anand.synchronossweatherapp

import android.app.Application
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import dev.anand.synchronossweatherapp.worker.RefreshWeatherWorker
import timber.log.Timber
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class SynchronossWeatherApp : Application(),
    Configuration.Provider {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        createPeriodicWorkRequest()

    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(
                if (BuildConfig.DEBUG) android.util.Log.DEBUG else
                    android.util.Log.ERROR
            )
            .build()
    }
    private fun createPeriodicWorkRequest() {
        val refreshWeatherWorker = PeriodicWorkRequestBuilder<RefreshWeatherWorker>(15, TimeUnit.MINUTES)
            .setConstraints(RefreshWeatherWorker.constraints)
            .addTag("refreshWeather")
            .build()
        val workManager= WorkManager.getInstance(this)
        workManager .enqueueUniquePeriodicWork(
            "periodicWeatherDownload",
            ExistingPeriodicWorkPolicy.KEEP,
            refreshWeatherWorker
        )
    }




}