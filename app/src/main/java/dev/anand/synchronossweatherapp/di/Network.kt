package dev.anand.synchronossweatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.anand.synchronossweatherapp.data.api.CurrentWeatherService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Network {

    @Provides
    @Singleton
    fun provideWeatherService(): CurrentWeatherService {
        return CurrentWeatherService.create()
    }


}