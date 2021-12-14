package com.example.wheaterapp.domain

class GetWeatherDataUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(cityName: String):Result = repository.getWeatherData(cityName)
}