package dev.anand.synchronossweatherapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = WeatherInfoEntity.TABLE_NAME)
data class WeatherInfoEntity(
  @PrimaryKey
  val dt: Long,
  val name: String,
  val main: String,
  val description: String,
  val temp: Double,
  val icon: String,
  val lat: Double,
  val lng: Double
) {
  companion object {
    const val TABLE_NAME = "weather_info"
  }
}