package dev.anand.synchronossweatherapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.anand.synchronossweatherapp.domain.repository.SynchronossWeatherRepository
import dev.anand.synchronossweatherapp.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SynchronosWeatherInfoViewModel @Inject internal constructor(
  private val repository: SynchronossWeatherRepository
) : ViewModel() {

  var state by mutableStateOf(SynchronosWeatherInfoState())

  init {
    getWeather(10.0, 78.0)
  }


  private fun getWeather(lat: Double, lng: Double) {
    Timber.d("getWeather in ViewModel: $lat , $lng")
    viewModelScope.launch {
      repository.getWeather(false, lat, lng).collect { result ->
        when (result) {
          is Resource.Success -> {
            delay(3000L)
            result.data?.let { weatherList ->
              state = state.copy(
                weatherInfoList = weatherList
              )
            }
          }
          is Resource.Error -> Unit
          is Resource.Loading -> {
            state = state.copy(isLoading = result.isLoading)
          }
        }
      }
    }
  }
}