package dev.anand.synchronossweatherapp.data.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.anand.synchronossweatherapp.domain.CurrentWeather
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Entity(tableName = WeatherInfo.TABLE_NAME)
data class WeatherInfo(
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


fun WeatherInfo.asDomainModel(): CurrentWeather =

    CurrentWeather(
        dt = this.dt.toDate(),
        name = this.name,
        main = this.main,
        description = this.description,
        temp = this.temp,
        icon = this.icon,
        lat = this.lat,
        lng = this.lng
    )


fun Long.toDate(): String {

    println(this.toString())

    //val dateTimeFormatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy  hh:mm a");
    val dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
    val instant = Instant.ofEpochMilli(this * 1000)
    val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return dateTimeFormatter.format(date)
}