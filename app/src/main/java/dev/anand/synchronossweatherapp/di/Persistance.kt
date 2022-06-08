package dev.anand.synchronossweatherapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.anand.synchronossweatherapp.R
import dev.anand.synchronossweatherapp.data.db.WeatherDatabase
import dev.anand.synchronossweatherapp.data.db.dao.CurrentWeatherDAO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Persistance {

    @Provides
    @Singleton
    fun provideImageDAO(database:WeatherDatabase): CurrentWeatherDAO {
        return database.weatherDao()
    }

    @Provides
    @Singleton
    fun provideAppDataBase(application: Application):WeatherDatabase{
        return  Room.databaseBuilder(application,WeatherDatabase::class.java,
        application.getString(R.string.database))
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}