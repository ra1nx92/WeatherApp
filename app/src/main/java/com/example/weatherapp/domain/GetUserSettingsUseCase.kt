package com.example.weatherapp.domain

class GetUserSettingsUseCase(private val repository: WeatherRepository) {
    operator fun invoke(cityName:String?):String? = repository.getUserSettings()
}