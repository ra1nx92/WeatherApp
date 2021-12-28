package com.example.weatherapp.domain

data class WeatherInfo(
    val temp: Double,
    val country: String,
    val name: String,
    val humidity: Int,
    val speed: Double,
    val feelsLike: Double,
    val pressure: Int,
    val icon: String
)
