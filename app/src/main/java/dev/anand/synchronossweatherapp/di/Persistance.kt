package dev.anand.synchronossweatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.anand.synchronossweatherapp.data.local.WeatherDatabase
import dev.anand.synchronossweatherapp.data.local.WeatherInfoDao

@Module
@InstallIn(SingletonComponent::class)
object Persistance {

    @Provides
    fun provideWeatherInfoDAO(database: WeatherDatabase): WeatherInfoDao {
        return database.weatherInfoDao()
    }
}