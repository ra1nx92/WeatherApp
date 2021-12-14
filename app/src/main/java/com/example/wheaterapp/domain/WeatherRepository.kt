package com.example.wheaterapp.domain

interface WeatherRepository {
   suspend fun getWeatherData(cityName: String):Result
}