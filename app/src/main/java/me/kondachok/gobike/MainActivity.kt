package me.kondachok.gobike

import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import me.kondachok.gobike.location.LocationProvider
import me.kondachok.gobike.ui.theme.GoBikeTheme
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {

    private val locationProvider: LocationProvider by inject()
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
                    MainApp(location)
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

@Composable
fun MainApp(location: Location?) {
    if (location != null) {
        Text("Location: la ${location.latitude}, lo ${location.longitude}")
    } else {
        Text("No location")
    }
}
