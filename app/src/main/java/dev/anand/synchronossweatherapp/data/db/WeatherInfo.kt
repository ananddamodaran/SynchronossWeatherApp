package dev.anand.synchronossweatherapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.anand.synchronossweatherapp.domain.CurrentWeather
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = WeatherInfo.TABLE_NAME)
data class WeatherInfo(
    @PrimaryKey
    val dt: Long,
    val name: String,
    val main: String,
    val description: String,
    val temp: Double,
    val icon: String
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
        icon = this.icon
    )


fun Long.toDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.US)
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return formatter.format(calendar.time)
}