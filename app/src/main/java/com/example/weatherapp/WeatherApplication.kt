package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.weatherViewModule
import com.example.weatherapp.di.weathersModel
import org.koin.core.context.startKoin

class WeatherApplication():Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(weatherViewModule, weathersModel)
        }
    }
}