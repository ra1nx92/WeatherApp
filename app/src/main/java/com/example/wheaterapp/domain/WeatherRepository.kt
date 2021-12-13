package com.example.wheaterapp.domain

import androidx.lifecycle.LiveData

interface WeatherRepository {
    fun getWeatherData():LiveData<List<WeatherInfo>>
}