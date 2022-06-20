package dev.anand.synchronossweatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.anand.synchronossweatherapp.data.local.WeatherInfoDao
import dev.anand.synchronossweatherapp.data.remote.OpenWeatherApi
import dev.anand.synchronossweatherapp.repository.AppRepository


@Module
@InstallIn(ViewModelComponent::class)
object Repository {
    @Provides
    @ViewModelScoped
    fun provideAppRepository(
        weatherService: OpenWeatherApi,
        weatherDAO: WeatherInfoDao
    ): AppRepository {
        return AppRepository(weatherService, weatherDAO
        )
    }
}
