package com.stefanusj.fruitstore

import android.app.Application
import com.stefanusj.fruitstore.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FruitStore : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FruitStore)
            modules(appModule)
        }
    }
}