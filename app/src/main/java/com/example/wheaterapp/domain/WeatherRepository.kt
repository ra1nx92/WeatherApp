package com.example.wheaterapp.domain

import androidx.lifecycle.LiveData

interface WeatherRepository {
   suspend fun getWeatherData():LiveData<List<WeatherInfo>>
}