package com.stefanusj.fruitstore.di

import com.stefanusj.fruitstore.data.AppRepository
import com.stefanusj.fruitstore.data.local.AppDatabase
import org.koin.dsl.module

@JvmField
val appModule = module {

    single { AppRepository(get()) }

    single { provideDatabase(get()) }

    single { provideOkHttpClient() }

    single { get<AppDatabase>().localService() }

    single { provideWebService(get()) }

}