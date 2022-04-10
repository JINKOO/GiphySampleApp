package com.kjk.giphy

import android.app.Application
import com.kjk.giphy.data.GiphyRepository

class GiphyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        GiphyRepository.initialize(this)
    }
}