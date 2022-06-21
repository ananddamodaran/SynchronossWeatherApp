package dev.anand.synchronossweatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(weather: List<WeatherInfoEntity>)

    @Query("SELECT * FROM ${WeatherInfoEntity.TABLE_NAME}")
    fun getAll(): Flow<WeatherInfoEntity>

    @Query("SELECT * FROM ${WeatherInfoEntity.TABLE_NAME}")
    fun getWeather(): WeatherInfoEntity

    @Query("DELETE FROM ${WeatherInfoEntity.TABLE_NAME}")
    fun clear()

    @Query("SELECT * FROM ${WeatherInfoEntity.TABLE_NAME}")
    fun getWeatherList(): List<WeatherInfoEntity>
}