package com.semenov.reddit.presentation

import android.app.Application
import com.semenov.reddit.InstanceProvider

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        InstanceProvider.init(applicationContext)
    }
}