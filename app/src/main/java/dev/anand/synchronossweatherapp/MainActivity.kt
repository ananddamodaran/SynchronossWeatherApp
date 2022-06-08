package dev.anand.synchronossweatherapp

import android.Manifest
import android.content.Context
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import dev.anand.synchronossweatherapp.data.model.CurrentWeatherForecastResponse
import dev.anand.synchronossweatherapp.ui.screen.home.HomeScreenViewModel
import dev.anand.synchronossweatherapp.ui.theme.SynchronossWeatherAppTheme
import timber.log.Timber


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private lateinit var locationRequest:LocationRequest
    private val location = mutableStateOf("Location")
    private val viewModel: HomeScreenViewModel by viewModels()
    private var locationUpdateObserver: Observer<CurrentWeatherForecastResponse?> =
        Observer<CurrentWeatherForecastResponse?> { weatherRResponse ->
            val city = weatherRResponse?.city?.name
                location.value = city?:""
                Timber.d("weatherRResponse $weatherRResponse")

        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.locationMutableLiveData.observe(this, locationUpdateObserver)
        setContent {
            SynchronossWeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(location.value)
                }
            }
        }
         locationRequest = LocationRequest.create();
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY;
        locationRequest.interval = 5000;
        locationRequest.fastestInterval = 2000;
        getCurrentLocation()



    }


private fun getCurrentLocation() {
    Timber.d("getCurrentLocation")
    if (ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        if (isGPSEnabled()) {
            LocationServices.getFusedLocationProviderClient(this@MainActivity)
                .requestLocationUpdates(locationRequest, object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
                        super.onLocationResult(locationResult)
                        LocationServices.getFusedLocationProviderClient(this@MainActivity)
                            .removeLocationUpdates(this)
                        if (locationResult.locations.size > 0) {
                            val index = locationResult.locations.size - 1
                            latitude = locationResult.locations[index].latitude
                            longitude = locationResult.locations[index].longitude
                            Timber.i("Location - Lat: $latitude Lng: $longitude")
                            viewModel.getWeather(latitude,longitude)
                        }
                    }
                }, Looper.getMainLooper())
        } else {
            turnOnGPS()
        }
    } else {
        requestPermissions(arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION), 1)
    }
}

private fun turnOnGPS() {
    val builder = LocationSettingsRequest.Builder()
        .addLocationRequest(locationRequest)
    builder.setAlwaysShow(true)
    val result: Task<LocationSettingsResponse> =
        LocationServices.getSettingsClient(applicationContext)
            .checkLocationSettings(builder.build())
    result.addOnCompleteListener(OnCompleteListener<LocationSettingsResponse?> { task ->
        try {
            val response = task.getResult(ApiException::class.java)
            Toast.makeText(this@MainActivity, "GPS is already tured on", Toast.LENGTH_SHORT).show()
        } catch (e: ApiException) {
            when (e.statusCode) {
                LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                    val resolvableApiException = e as ResolvableApiException
                    resolvableApiException.startResolutionForResult(this@MainActivity, 2)
                } catch (ex: SendIntentException) {
                    ex.printStackTrace()
                }
                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {}
            }
        }
    })
}

private fun isGPSEnabled(): Boolean {
    var locationManager: LocationManager? = null
    var isEnabled = false
    if (locationManager == null) {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
    }
    isEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
    return isEnabled
}
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SynchronossWeatherAppTheme {
        Greeting("Android")
    }
}
