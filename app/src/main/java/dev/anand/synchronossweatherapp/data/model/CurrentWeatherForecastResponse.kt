package dev.anand.synchronossweatherapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeatherForecastResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val city: City,
    val list: List<Data>

) : Parcelable

@Parcelize
data class Data(
    val dt: Long,
    val weather: List<Weather>,
    val main: Main,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Double,
    val sys: Sys,
    val dt_txt: String

) : Parcelable

@Parcelize
data class Clouds(
    val all: Int
) : Parcelable

@Parcelize
data class Coord(
    val lat: Double,
    val lon: Double
) : Parcelable

@Parcelize
data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
) : Parcelable

@Parcelize
data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
) : Parcelable

@Parcelize
data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
) : Parcelable

@Parcelize
data class Wind(
    val deg: Int,
    val speed: Double
) : Parcelable

@Parcelize
data class City(
    val id: Int,
    val name: String,
    val coord: Coord
) : Parcelable