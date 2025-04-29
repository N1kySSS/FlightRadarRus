package com.ortin.flightradar

import android.app.Application
import com.ortin.flightradar.di.provideModule
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class FlightRadarApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val mapKitApiKey = getString(R.string.MAPKIT_API_KEY)
        val myEmail = getString(R.string.MY_EMAIL)
        val myPassword = getString(R.string.MY_PASSWORD)

        MapKitFactory.setApiKey(mapKitApiKey)
        MapKitFactory.initialize(this)

        startKoin {
            androidLogger()
            androidContext(this@FlightRadarApp)

            modules(provideModule)

            modules(
                module {
                    single(named("MY_EMAIL")) { myEmail }
                    single(named("MY_PASSWORD")) { myPassword }
                }
            )
        }
    }
}
