package com.example.wheaterapp.data.repository

import com.example.wheaterapp.data.network.model.WeatherModel
import com.example.wheaterapp.data.network.service.WeatherApi
import com.example.wheaterapp.domain.Result
import com.example.wheaterapp.domain.WeatherDomainMapper
import com.example.wheaterapp.domain.WeatherRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class WeatherRepositoryImpl(
    private val apiService: WeatherApi,
    private val mapper:WeatherDomainMapper)
    : WeatherRepository {

    private val gson = Gson()
    private val type: Type = object : TypeToken<WeatherModel>() {}.type


    override suspend fun getWeatherData(cityName: String): Result {
        return try {
            val result = apiService.getWeatherData(cityName).string()
            val gsonResult = gson.fromJson<WeatherModel>(result, type)
            Result.Success(mapper.map(gsonResult))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}