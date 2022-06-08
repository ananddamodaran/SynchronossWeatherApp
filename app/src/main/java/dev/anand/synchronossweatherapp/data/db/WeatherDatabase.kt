package dev.anand.synchronossweatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.anand.synchronossweatherapp.data.db.dao.CurrentWeatherDAO

@Database(
    entities = [
        CurrentWeatherEntity::class
               ], version = 1,exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): CurrentWeatherDAO
}