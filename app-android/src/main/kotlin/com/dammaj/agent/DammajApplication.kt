package com.dammaj.agent

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DammajApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
