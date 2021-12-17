package com.example.weatherapp.domain

import java.net.UnknownHostException

sealed class Result {
    object Loading : Result()
    data class Success(val weatherInfo: WeatherInfo) : Result()
    data class Error(val exception: Exception) : Result()
    data class NetworkError(val exception: UnknownHostException) : Result()
}