package dev.anand.synchronossweatherapp.presentation.home

import com.google.common.truth.Truth.assertThat
import dev.anand.synchronossweatherapp.MainCoroutineRule
import dev.anand.synchronossweatherapp.data.repository.SynchronossWeatherRepositoryFake
import dev.anand.synchronossweatherapp.domain.model.WeatherInfo
import dev.anand.synchronossweatherapp.domain.repository.SynchronossWeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SynchronosWeatherInfoViewModelTest {
  @get:Rule
  val coroutineRule = MainCoroutineRule()
  private lateinit var viewModel: SynchronosWeatherInfoViewModel
  private lateinit var repositoryFake: SynchronossWeatherRepository

  @Before
  fun setUp() {
    repositoryFake = SynchronossWeatherRepositoryFake()
    viewModel = SynchronosWeatherInfoViewModel(
      repository = repositoryFake
    )
  }

  @Test
  fun `WeatherInfo are properly mapped to state`() {
    val weatherInfos = WeatherInfo()
    coroutineRule.dispatcher.scheduler.advanceUntilIdle()
    assertThat(viewModel.state.weatherInfoList).isEqualTo(listOf(weatherInfos))
  }
}