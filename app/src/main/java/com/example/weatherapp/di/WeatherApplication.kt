package com.example.weatherapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(screenModule, applicationModule)
            androidContext(this@WeatherApplication)
        }
    }
}