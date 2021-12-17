package com.example.wheaterapp

import android.app.Application
import com.example.wheaterapp.di.weatherViewModule
import com.example.wheaterapp.di.weathersModel
import org.koin.core.context.startKoin

class WeatherApplication():Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(weatherViewModule, weathersModel)
        }
    }
}