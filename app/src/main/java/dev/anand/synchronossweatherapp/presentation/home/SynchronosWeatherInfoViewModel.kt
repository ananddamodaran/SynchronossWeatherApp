package dev.anand.synchronossweatherapp.presentation.home

import androidx.lifecycle.ViewModel
import dev.anand.synchronossweatherapp.domain.repository.SynchronossWeatherRepository
import javax.inject.Inject

class SynchronosWeatherInfoViewModel @Inject internal constructor(
    private val repository: SynchronossWeatherRepository) : ViewModel(){

}