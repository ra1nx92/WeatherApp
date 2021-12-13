package com.example.wheaterapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.wheaterapp.data.network.service.WeatherApiFactory
import com.example.wheaterapp.domain.WeatherInfo
import com.example.wheaterapp.domain.WeatherRepository

class WeatherRepositoryImpl(private val application: Application):WeatherRepository {

    private val apiService = WeatherApiFactory.getDataService(cityName = "")

    override suspend fun getWeatherData(): LiveData<List<WeatherInfo>> {
        TODO()
    }
}