package com.example.wheaterapp.domain

import java.lang.Exception

sealed class Result {
    object Loading:Result()
    data class Success(val weatherInfo:WeatherInfo):Result()
    data class Error(val exception:Exception):Result()
}