package com.example.weatherapp.domain.use_cases

import com.example.weatherapp.domain.repository.UserSettingsRepository

class GetLoadCityUseCase(private val repository: UserSettingsRepository) {
   operator fun invoke() = repository.loadCity()
}