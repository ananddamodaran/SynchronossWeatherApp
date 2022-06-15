package dev.anand.synchronossweatherapp.domain

data class CurrentWeather(
    val dt: String,
    val name: String,
    val main: String,
    val description: String,
    val temp: Double,
    val icon: String
)