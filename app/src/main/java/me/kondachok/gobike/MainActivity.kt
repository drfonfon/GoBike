package me.kondachok.gobike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import me.kondachok.gobike.location.DefaultLocationProvider
import me.kondachok.gobike.ui.theme.GoBikeTheme


class MainActivity : ComponentActivity() {

    private val locationProvider by lazy { DefaultLocationProvider(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            GoBikeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val location by locationProvider.location.collectAsState()
                    Column {
                        if (location != null) {
                            val singapore = LatLng(location!!.latitude, location!!.longitude)
                            val cameraPositionState = rememberCameraPositionState {
                                position = CameraPosition.fromLatLngZoom(singapore, 10f)
                            }
                            GoogleMap(
                                modifier = Modifier.fillMaxSize(),
                                cameraPositionState = cameraPositionState
                            ) {
                                Marker(
                                    state = MarkerState(position = singapore),
                                    title = "Singapore",
                                    snippet = "Marker in Singapore"
                                )
                            }
                        } else {
                            Text(text = "No location")
                        }
                    }

                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        locationProvider.start()
    }

    override fun onStop() {
        super.onStop()
        locationProvider.stop()
    }
}
