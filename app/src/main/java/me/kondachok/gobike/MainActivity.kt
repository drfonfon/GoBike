package me.kondachok.gobike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import me.kondachok.gobike.location.DefaultLocationProvider
import me.kondachok.gobike.ui.theme.GoBikeTheme
import timber.log.Timber
import timber.log.Timber.*


class MainActivity : ComponentActivity() {

    private val locationProvider by lazy { DefaultLocationProvider(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        Timber.plant(DebugTree())
        super.onCreate(savedInstanceState)
        setContent {
            GoBikeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val location by locationProvider.location.collectAsState()
                    if (location != null) {
                        Text(text = "Current location: la: ${location!!.latitude}, lo: ${location!!.longitude}")
                    } else {
                        Text(text = "No location")
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
