package com.kjk.giphy

import android.app.Application
import timber.log.Timber

/**
 *  Application Class
 *  다른 모든 class들 보다 먼저 실행된다.
 *  라이브러리 초기화 등에 사용됨.
 */
class GiphyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Timber 라이브러리 초기화.
        Timber.plant(Timber.DebugTree())
    }
}