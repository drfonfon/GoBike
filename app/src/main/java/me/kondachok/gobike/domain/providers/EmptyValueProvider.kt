package me.kondachok.gobike.domain.providers

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object EmptyValueProvider : ValueProvider {
    override val value: StateFlow<String> = MutableStateFlow("")
}
