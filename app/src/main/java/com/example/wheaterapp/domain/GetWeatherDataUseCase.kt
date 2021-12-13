package com.example.wheaterapp.domain

class GetWeatherDataUseCase(private val repository: WeatherRepository) {
    operator fun invoke() = repository.getWeatherData()
}