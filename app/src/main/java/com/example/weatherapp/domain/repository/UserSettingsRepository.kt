package com.example.weatherapp.domain.repository

interface UserSettingsRepository {
    fun saveCity(cityName: String)
    fun loadCity(): String?
}