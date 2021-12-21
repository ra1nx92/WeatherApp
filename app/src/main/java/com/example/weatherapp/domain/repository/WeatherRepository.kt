package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.Result

interface WeatherRepository {
   suspend fun getWeatherData(cityName: String): Result
}