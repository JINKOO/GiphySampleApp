package com.kjk.giphy

import android.app.Application
import timber.log.Timber

class GiphyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}