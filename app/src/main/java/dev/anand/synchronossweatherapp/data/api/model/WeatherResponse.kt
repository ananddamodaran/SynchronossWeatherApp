package dev.anand.synchronossweatherapp.data.api.model

import dev.anand.synchronossweatherapp.data.db.WeatherInfo


data class WeatherResponse(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

fun WeatherResponse.asDatabaseModel(): WeatherInfo =
    WeatherInfo(
        dt = this.dt.toLong(),
        name = this.name,
        main = this.weather[0].main,
        description = this.weather[0].description,
        temp = this.main.temp,
        icon = this.weather[0].icon,
        lat = this.coord.lat,
        lng = this.coord.lon
    )


