package com.stefanusj.fruitstore.di

import android.app.Application
import androidx.room.Room
import com.stefanusj.fruitstore.data.local.AppDatabase
import com.stefanusj.fruitstore.data.network.SkipNetworkInterceptor
import com.stefanusj.fruitstore.data.network.WebService
import com.stefanusj.fruitstore.helper.DATABASE_NAME
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(SkipNetworkInterceptor())
        .build()

fun provideWebService(okHttpClient: OkHttpClient): WebService =
    Retrofit.Builder()
        .baseUrl("http://localhost/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WebService::class.java)

fun provideDatabase(application: Application): AppDatabase =
    Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        DATABASE_NAME
    ).build()