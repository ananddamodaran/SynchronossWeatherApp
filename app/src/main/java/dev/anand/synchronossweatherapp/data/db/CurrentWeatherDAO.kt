package dev.anand.synchronossweatherapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.anand.synchronossweatherapp.data.db.CurrentWeatherEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(weather: List<CurrentWeatherEntity>)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): Flow<List<CurrentWeatherEntity>>
}