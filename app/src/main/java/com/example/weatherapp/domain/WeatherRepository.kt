package com.example.weatherapp.domain

interface WeatherRepository {
   suspend fun getWeatherData(cityName: String):Result
   fun getUserSettings():String?
}