package de.pirrung.crypto

import android.app.Application
import de.pirrung.crypto.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoinApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CoinApp)
            modules(appModule)
        }
    }

}