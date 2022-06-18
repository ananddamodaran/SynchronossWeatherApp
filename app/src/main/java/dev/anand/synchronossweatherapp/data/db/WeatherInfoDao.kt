package dev.anand.synchronossweatherapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(weather: List<WeatherInfo>)

    @Query("SELECT * FROM ${WeatherInfo.TABLE_NAME}")
    fun getAll(): Flow<List<WeatherInfo>>

    @Query("SELECT * FROM ${WeatherInfo.TABLE_NAME}")
    fun getWeather(): Flow<WeatherInfo>
    @Query("DELETE FROM ${WeatherInfo.TABLE_NAME}")
    fun clear()
}