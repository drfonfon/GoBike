package me.kondachok.gobike.domain.providers

import kotlinx.coroutines.flow.StateFlow

interface ValueProvider {
    val value: StateFlow<String>
}
