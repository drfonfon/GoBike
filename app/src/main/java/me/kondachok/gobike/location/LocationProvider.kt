package me.kondachok.gobike.location

import android.location.Location
import kotlinx.coroutines.flow.StateFlow

interface LocationProvider {
    val location: StateFlow<Location?>

    fun start()

    fun stop()
}
