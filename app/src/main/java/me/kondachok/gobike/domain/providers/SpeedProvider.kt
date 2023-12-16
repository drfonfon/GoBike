package me.kondachok.gobike.domain.providers

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import me.kondachok.gobike.location.LocationProvider

class SpeedProvider(
    locationProvider: LocationProvider,
    scope: CoroutineScope
) : ValueProvider {
    override val value: StateFlow<String> = locationProvider
        .location
        .map { floatFormat.format(it?.speed ?: 0f) }
        .stateIn(scope, SharingStarted.Lazily, "")

}

val floatFormat = DecimalFormat("0.00", DecimalFormatSymbols(Locale.US)).apply {
    roundingMode = RoundingMode.FLOOR
}
