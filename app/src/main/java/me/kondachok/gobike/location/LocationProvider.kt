package me.kondachok.gobike.location

import android.location.Location
import kotlinx.coroutines.flow.StateFlow


interface LocationProvider : AnyLifecycle<Unit> {
    val location: StateFlow<Location?>
}
