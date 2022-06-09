package dev.anand.synchronossweatherapp.ui.screen.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.WorkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.anand.synchronossweatherapp.data.model.CurrentWeatherForecastResponse
import dev.anand.synchronossweatherapp.repository.AppRepository
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: AppRepository,

): ViewModel() {




    var locationMutableLiveData: MutableLiveData<CurrentWeatherForecastResponse?> = MutableLiveData()

    init {
        locationMutableLiveData.value = null
    }
    fun getWeather(latitude:Double, longitude:Double){
        Timber.d("UpdateWeatherWorker getWeather: $latitude , $longitude")

        val mainActivityJob = Job()
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            Timber.e("Error $exception" )
        }
        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch(errorHandler) {
            locationMutableLiveData.value = repository.getWeather(latitude,longitude)

        }
    }
}