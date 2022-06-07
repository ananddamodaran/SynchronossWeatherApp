package dev.anand.synchronossweatherapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.anand.synchronossweatherapp.data.db.CurrentWeatherEntity

@Dao
interface CurrentWeatherDAO {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: CurrentWeatherEntity)
    @Query("SELECT * FROM CurrentWeatherEntity")
    fun getWeather(): LiveData<CurrentWeatherEntity>
}