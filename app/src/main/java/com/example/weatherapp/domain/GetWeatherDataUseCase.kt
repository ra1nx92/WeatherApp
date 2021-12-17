package com.example.weatherapp.domain

class GetWeatherDataUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(cityName: String):Result = repository.getWeatherData(cityName)
}