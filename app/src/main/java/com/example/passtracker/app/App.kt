package com.example.passtracker.app

import android.app.Application
import com.example.passtracker.app.di.appModule
import com.example.passtracker.app.di.dataModule
import com.example.passtracker.app.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    appModule
                )
            )
        }
    }
}