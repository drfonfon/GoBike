package me.kondachok.gobike.di

import me.kondachok.gobike.location.AnyLifecycle
import me.kondachok.gobike.location.DefaultLocationProvider
import me.kondachok.gobike.location.LocationProvider
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::DefaultLocationProvider) {
        bind<LocationProvider>()
        bind<AnyLifecycle<Unit>>()
    }
}
