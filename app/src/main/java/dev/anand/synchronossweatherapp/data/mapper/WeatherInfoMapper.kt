package dev.anand.synchronossweatherapp.data.mapper

import dev.anand.synchronossweatherapp.data.local.WeatherInfoEntity
import dev.anand.synchronossweatherapp.data.local.toDate
import dev.anand.synchronossweatherapp.data.remote.dto.WeatherInfoDto
import dev.anand.synchronossweatherapp.domain.model.Coord
import dev.anand.synchronossweatherapp.domain.model.WeatherInfo

fun WeatherInfoEntity.toWeatherInfo(): WeatherInfo =

    WeatherInfo(
        dt = dt.toDate(),
        name = name,
        main = main,
        description = description,
        temp = temp,
        icon = icon,
        coord = Coord(lat,lng)
    )


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
