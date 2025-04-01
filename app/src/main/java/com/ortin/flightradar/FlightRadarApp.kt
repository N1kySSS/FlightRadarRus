package com.ortin.flightradar

import android.app.Application
import com.ortin.flightradar.presentation.di.providePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FlightRadarApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FlightRadarApp)

            modules(providePresentationModule)
        }
    }
}
