package com.example.weatherapp.domain.use_cases

import com.example.weatherapp.domain.repository.UserSettingsRepository

class GetSaveCityUseCase(private val repository: UserSettingsRepository) {
   operator fun invoke(cityName: String) = repository.saveCity(cityName)

}