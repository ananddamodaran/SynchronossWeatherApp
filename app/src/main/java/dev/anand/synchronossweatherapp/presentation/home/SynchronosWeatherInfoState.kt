package dev.anand.synchronossweatherapp.presentation.home

import dev.anand.synchronossweatherapp.domain.model.Coord
import dev.anand.synchronossweatherapp.domain.model.WeatherInfo

data class SynchronosWeatherInfoState(
    val weatherInfoList: List<WeatherInfo> = emptyList(),
    val isLoading: Boolean = false,
    val coord: Coord = Coord(0.0, 0.0)
)
