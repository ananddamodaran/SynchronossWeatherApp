package dev.anand.synchronossweatherapp.domain.model

data class WeatherInfo(
    val dt: String="Updated at ",
    val name: String="Location",
    val main: String="",
    val description: String="",
    val temp: Double=0.0,
    val icon: String="",
    val coord: Coord=Coord(0.0,0.0)
)
data class Coord(
    val lat:Double,
    val lng:Double
)