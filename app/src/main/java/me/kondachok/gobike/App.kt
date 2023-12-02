package me.kondachok.gobike

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class App: Application() {

    override fun onCreate() {
        super.onCreate()

    }

    private fun initTimber() {
        Timber.plant(DebugTree())
    }
}
