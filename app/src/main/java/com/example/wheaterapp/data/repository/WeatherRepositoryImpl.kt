package com.example.wheaterapp.data.repository

import androidx.lifecycle.LiveData
import com.example.wheaterapp.data.maper.WeatherMapper
import com.example.wheaterapp.data.network.service.WeatherApiFactory
import com.example.wheaterapp.domain.WeatherInfo
import com.example.wheaterapp.domain.WeatherRepository

class WeatherRepositoryImpl() : WeatherRepository {

    private val apiService = WeatherApiFactory.dataService
    private val mapper = WeatherMapper()

    override suspend fun getWeatherData(): LiveData<List<WeatherInfo>> {
        val weatherModel = apiService.getWeatherData()
        val weatherInfo = mapper.mapApiDataToDomain(weatherModel)
    }
}