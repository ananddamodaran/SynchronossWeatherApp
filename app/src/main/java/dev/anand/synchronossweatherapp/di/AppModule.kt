package dev.anand.synchronossweatherapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.anand.synchronossweatherapp.data.local.WeatherDatabase
import dev.anand.synchronossweatherapp.data.local.WeatherInfoDao
import dev.anand.synchronossweatherapp.data.remote.OpenWeatherApi
import dev.anand.synchronossweatherapp.repository.AppRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherService(): OpenWeatherApi {
        return OpenWeatherApi.create()
    }

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): WeatherDatabase {
        return WeatherDatabase.getInstance(context)
    }


    @Provides
    @Singleton
    fun provideAppRepository(
        weatherApi: OpenWeatherApi,
        weatherDAO: WeatherInfoDao
    ): AppRepository {
        return AppRepository(
            weatherApi, weatherDAO
        )
    }
}