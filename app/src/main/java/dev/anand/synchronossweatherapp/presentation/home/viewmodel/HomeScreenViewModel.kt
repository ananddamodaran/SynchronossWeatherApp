package dev.anand.synchronossweatherapp.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.anand.synchronossweatherapp.data.repository.AppRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject internal constructor(
    private val repository: AppRepository,
) : ViewModel() {
    val weather = repository.weather

    fun getWeatherFlow(latitude: Double, longitude: Double) {
        Timber.d("Weather: $latitude , $longitude")
        viewModelScope.launch {
            repository.getWeatherFlow(latitude, longitude)
        }
    }

}