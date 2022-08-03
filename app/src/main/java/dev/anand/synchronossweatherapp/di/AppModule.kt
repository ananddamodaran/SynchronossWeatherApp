package dev.anand.synchronossweatherapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.anand.synchronossweatherapp.data.local.WeatherDatabase
import dev.anand.synchronossweatherapp.data.local.WeatherInfoDao
import dev.anand.synchronossweatherapp.data.remote.OpenWeatherService
import dev.anand.synchronossweatherapp.data.repository.AppRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideOpenWeatherService(): OpenWeatherService {
        return OpenWeatherService.createHttpClient()
    }

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): WeatherDatabase {
        return WeatherDatabase.getInstance(context)
    }


    @Provides
    @Singleton
    fun provideAppRepository(
        weatherApi: OpenWeatherService,
        weatherDAO: WeatherInfoDao
    ): AppRepository {
        return AppRepository(
            weatherApi, weatherDAO
        )
    }


}