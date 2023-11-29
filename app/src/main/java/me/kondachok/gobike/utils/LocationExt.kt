package me.kondachok.gobike.utils

import android.location.Location
import android.os.Bundle

private const val KEY_TYPE = "Location_key_type"

fun Location.setType(type: String): Location = apply {
    val curExtras = extras ?: Bundle(1)
    curExtras.putString(KEY_TYPE, type)
    extras = curExtras
}

fun Location.getType(): String {
    return extras?.getString(KEY_TYPE) ?: "Off"
}
