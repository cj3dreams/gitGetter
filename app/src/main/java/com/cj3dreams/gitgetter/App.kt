package com.cj3dreams.gitgetter

import android.app.Application
import com.cj3dreams.gitgetter.di.dataSourceModule
import com.cj3dreams.gitgetter.di.downloadsViewModel
import com.cj3dreams.gitgetter.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(networkModule, dataSourceModule, downloadsViewModel)
        }
    }
}