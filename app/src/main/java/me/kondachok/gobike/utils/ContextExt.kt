package me.kondachok.gobike.utils

import android.Manifest.permission
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.LocationManager
import androidx.core.content.ContextCompat

val Context.locationManager: LocationManager
    get() = getSystemService(Context.LOCATION_SERVICE) as LocationManager

fun Context.isGranted(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PERMISSION_GRANTED
}

val Context.isFineLocationGranted: Boolean
    get() = isGranted(permission.ACCESS_FINE_LOCATION)

val Context.isCoarseLocationGranted: Boolean
    get() = isGranted(permission.ACCESS_COARSE_LOCATION)

val Context.isLocationPermissionsGranted: Boolean
    get() = isFineLocationGranted && isCoarseLocationGranted
