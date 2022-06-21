package dev.anand.synchronossweatherapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.anand.synchronossweatherapp.data.local.WeatherInfoDao
import dev.anand.synchronossweatherapp.data.remote.OpenWeatherApi
import dev.anand.synchronossweatherapp.data.repository.SynchronossWeatherRepositoryImpl
import dev.anand.synchronossweatherapp.domain.repository.SynchronossWeatherRepository
import dev.anand.synchronossweatherapp.repository.AppRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsSynchronossWeatherRepository(
        synchronossWeatherRepositoryImpl: SynchronossWeatherRepositoryImpl
    ): SynchronossWeatherRepository

}
