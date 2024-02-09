package dev.hyuwah.kalkulator

import android.app.Application
import dev.hyuwah.kalkulator.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KalkulatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KalkulatorApplication)
            modules(
                appModule
            )
        }
    }
}