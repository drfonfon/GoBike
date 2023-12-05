package me.kondachok.gobike.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Looper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import me.kondachok.gobike.utils.isLocationPermissionsGranted
import me.kondachok.gobike.utils.locationManager
import me.kondachok.gobike.utils.setType
import timber.log.Timber

class DefaultLocationProvider(private val context: Context) : LocationProvider {

    private val timber = Timber.tag(TAG_LOCATION)

    private val manager by lazy { context.locationManager }

    private val _location = MutableStateFlow<Location?>(null)
    override val location: StateFlow<Location?> = _location

    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            timber.d("Provider [$TYPE] get location: $location")
            _location.value = location.setType(TYPE)
        }

        override fun onProviderEnabled(provider: String) {
            timber.d("Provider [$TYPE] onProviderEnabled: $provider")
        }

        override fun onProviderDisabled(provider: String) {
            timber.d("Provider [$TYPE] onProviderDisabled: $provider")
            clear()
        }
    }

    @SuppressLint("MissingPermission")
    override fun start(args: Unit) {
        manager.removeUpdates(locationListener)
        if (!context.isLocationPermissionsGranted) {
            clear()
            timber.d("Provider [$TYPE] need permission")
            // TODO REQUEST PERMISSIONS
            return
        }
        manager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0L,
            0f,
            locationListener,
            Looper.myLooper()
        )
        timber.d("Provider [$TYPE] started")
    }

    override fun stop() {
        manager.removeUpdates(locationListener)
        clear()
        timber.d("Provider [$TYPE] stopped")
    }

    private fun clear() {
        _location.value = null
    }

    companion object {
        const val TYPE = "Default"
    }
}
