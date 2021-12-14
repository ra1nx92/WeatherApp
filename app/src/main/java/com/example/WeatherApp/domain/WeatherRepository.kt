package com.example.WeatherApp.domain

interface WeatherRepository {
   suspend fun getWeatherData(cityName: String):Result
}