package me.kondachok.gobike

import android.app.Application
import me.kondachok.gobike.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


class GoBikeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()

        startKoin {
            androidContext(this@GoBikeApplication)
            androidLogger()
            modules(appModule)
        }
    }

    private fun initTimber() {
        Timber.plant(DebugTree())
    }
}
