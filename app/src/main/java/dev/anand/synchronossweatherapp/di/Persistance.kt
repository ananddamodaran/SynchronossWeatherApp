package dev.anand.synchronossweatherapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.anand.synchronossweatherapp.data.local.WeatherDatabase
import dev.anand.synchronossweatherapp.data.local.WeatherInfoDao
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Persistance {

    @Provides
    fun provideWeatherInfoDAO(database: WeatherDatabase): WeatherInfoDao {
        return database.weatherInfoDao()
    }


    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): WeatherDatabase {
        Timber.tag("HILT").d("provideAppDataBase")
        return WeatherDatabase.getInstance(context)
    }
}