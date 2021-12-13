package com.example.wheaterapp.domain

data class WeatherInfo(
    val temp: Double,
    val country: String,
    val name: String,
    val humidity: Int,
    val speed: Double,
    val lat: Double,
    val lon: Double
)
