package dev.anand.synchronossweatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.anand.synchronossweatherapp.data.db.dao.CurrentWeatherDAO
import dev.anand.synchronossweatherapp.network.CurrentWeatherService
import dev.anand.synchronossweatherapp.repository.AppRepository

@Module
@InstallIn(ViewModelComponent::class)
object Repository {
    @Provides
    @ViewModelScoped
    fun provideAppRepository(
        weatherService: CurrentWeatherService,
        weatherDAO: CurrentWeatherDAO
    ):AppRepository{
        return AppRepository(weatherService,weatherDAO)
    }
}