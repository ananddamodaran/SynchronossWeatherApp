package dev.anand.synchronossweatherapp

import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import dagger.hilt.android.AndroidEntryPoint
import dev.anand.synchronossweatherapp.domain.model.WeatherInfo
import dev.anand.synchronossweatherapp.presentation.home.viewmodel.HomeScreenViewModel
import dev.anand.synchronossweatherapp.ui.theme.SynchronossWeatherAppTheme
import dev.anand.synchronossweatherapp.util.Constants.REQUEST_CHECK_SETTINGS
import dev.anand.synchronossweatherapp.worker.UpdateWeatherWorker
import timber.log.Timber


@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var lastLocation: Location? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private var locationUpdateState = false

    val  locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)
            lastLocation = p0.lastLocation
            if(lastLocation!=null) {
                Timber.d("lastloc- ${lastLocation?.latitude}, ${lastLocation?.longitude}")
                Timber.d("lastloc- ${p0.lastLocation?.latitude}, ${p0.lastLocation?.longitude}")
                fusedLocationClient.removeLocationUpdates(this)
                Timber.d("lastloc weather is null")
                val request = OneTimeWorkRequestBuilder<UpdateWeatherWorker>()
                           .setInputData(workDataOf("lat" to p0.lastLocation?.latitude.toString(),
                           "lng" to p0.lastLocation?.longitude.toString()))

                          .build()
                WorkManager.getInstance(applicationContext).enqueue(request)

            }
        }
    }

    private val viewModel: HomeScreenViewModel by viewModels()
    private val currentWeatherState = mutableStateOf<WeatherInfo>(WeatherInfo())


    private var weatherUpdateObserver: Observer<WeatherInfo?> =
        Observer<WeatherInfo?> { currentWeather ->
            Timber.d("currentWeather $currentWeather")
            currentWeather?.let {
                currentWeatherState.value = it
            }

        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel.weather.observe(this, weatherUpdateObserver)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            SynchronossWeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherCard(
                        currentWeatherState.value,
                        modifier = Modifier
                            .padding(16.dp)
                            .background(
                                color = Color.Yellow
                            )
                    )
                }
            }

        }
        createLocationRequest()
    }




    var requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if(isGranted){
            Timber.d("Permision granted")

            locationUpdateState = true
            startLocationUpdates()
        }else{
            Timber.d("Permision denied")
        }

    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }


    public override fun onResume() {
        super.onResume()
        if (!locationUpdateState) {
            startLocationUpdates()
        }
    }
    private fun createLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)


        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())


        task.addOnSuccessListener {
            locationUpdateState = true
            startLocationUpdates()
        }
        task.addOnFailureListener { e ->
            locationUpdateState = false


            if (e is ResolvableApiException) {
                try {


                    e.startResolutionForResult(this@MainActivity,
                        REQUEST_CHECK_SETTINGS)
                } catch (_: IntentSender.SendIntentException) {

                }
            }
        }
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION);

            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null /* Looper */)
    }

}



