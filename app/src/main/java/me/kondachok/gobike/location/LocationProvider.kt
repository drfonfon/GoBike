package me.kondachok.gobike.location

import android.location.Location
import kotlinx.coroutines.flow.StateFlow

interface LocationProvider {
    val type: String
    val location: StateFlow<Location?>
}
