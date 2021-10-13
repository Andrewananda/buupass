package com.devstart.buupass

import android.app.Application
import com.devstart.buupass.util.Prefs
import dagger.hilt.android.HiltAndroidApp

val prefs: Prefs by lazy {
    BuuPass.prefs!!
}

@HiltAndroidApp
class BuuPass : Application() {
    companion object {
        var prefs: Prefs? = null
        lateinit var instance: BuuPass
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        prefs = Prefs(applicationContext)
    }
}