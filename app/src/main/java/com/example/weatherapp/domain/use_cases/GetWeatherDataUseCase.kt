package com.example.weatherapp.domain.use_cases

import com.example.weatherapp.domain.Result
import com.example.weatherapp.domain.repository.WeatherRepository

class GetWeatherDataUseCase(private val repository: WeatherRepository) {
   suspend operator fun invoke(cityName: String): Result = repository.getWeatherData(cityName)
}