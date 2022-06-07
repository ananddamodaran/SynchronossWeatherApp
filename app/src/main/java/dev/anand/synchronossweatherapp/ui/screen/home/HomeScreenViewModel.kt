package dev.anand.synchronossweatherapp.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.anand.synchronossweatherapp.repository.AppRepository
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: AppRepository

): ViewModel() {

    fun getWeather(latitude:Double,logitude:Double){

        val mainActivityJob = Job()
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            Log.d("Weather","error")
        }
        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch(errorHandler) {
            repository.getWeather(latitude,logitude)

        }
    }
}