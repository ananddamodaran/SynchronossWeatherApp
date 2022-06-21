package dev.anand.synchronossweatherapp.domain.model

data class WeatherInfo(
    val dt: String = "",
    val name: String = "",
    val main: String = "",
    val description: String = "",
    val temp: Double = 0.0,
    val icon: String = "",
    val coord: Coord = Coord(10.0, 78.0)
)

data class Coord(
    val lat: Double,
    val lng: Double
)