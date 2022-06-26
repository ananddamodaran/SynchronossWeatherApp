package dev.anand.synchronossweatherapp.domain

data class CurrentWeather(
    val dt: String="",
    val name: String="Location",
    val main: String="",
    val description: String="",
    val temp: Double=0.0,
    val icon: String="",
    val lat: Double=0.0,
    val lng:Double=0.0
)