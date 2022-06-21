package dev.anand.synchronossweatherapp.data.mapper

import dev.anand.synchronossweatherapp.data.local.WeatherInfoEntity
import dev.anand.synchronossweatherapp.data.remote.dto.WeatherInfoDto
import dev.anand.synchronossweatherapp.domain.model.Coord
import dev.anand.synchronossweatherapp.domain.model.WeatherInfo
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun WeatherInfoEntity.toWeatherInfo(): WeatherInfo {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.getDefault())
    val instant = Instant.ofEpochMilli(dt * 1000)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    val dateTime = dateTimeFormatter.format(localDateTime)
    return WeatherInfo(
        dt = dateTime,
        name = name,
        main = main,
        description = description,
        temp = temp,
        icon = icon,
        coord = Coord(lat, lng)
    )
}


fun WeatherInfoDto.toWeatherInfoEntity(): WeatherInfoEntity =
    WeatherInfoEntity(
        dt = this.dt.toLong(),
        name = this.name,
        main = this.weather[0].main,
        description = this.weather[0].description,
        temp = this.main.temp,
        icon = this.weather[0].icon,
        lat = this.coord.lat,
        lng = this.coord.lon
    )
