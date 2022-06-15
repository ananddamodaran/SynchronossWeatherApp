package dev.anand.synchronossweatherapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.anand.synchronossweatherapp.repository.AppRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject internal constructor(
    private val repository: AppRepository,

    ) : ViewModel() {
    val weather = repository.weather
    init {
        //  getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            repository.getAll().catch {
                Timber.e(it.localizedMessage)
            }
                .collect {
                    Timber.d("DB Size: ${it.size}")
                }
        }
    }


    fun getWeatherFlow(latitude: Double, longitude: Double) {
        Timber.d("UpdateWeatherWorker getWeather: $latitude , $longitude")
        viewModelScope.launch {
            repository.getWeatherFlow(latitude, longitude)
        }
    }

}