package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.applicationModule
import com.example.weatherapp.di.screenModule
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