package com.example.weatherapp.data.repository_impl

import com.example.weatherapp.data.network.model.WeatherModel
import com.example.weatherapp.data.network.service.WeatherApi
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.data.mapper.WeatherMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import com.example.weatherapp.domain.Result

class WeatherRepositoryImpl(
    private val apiService: WeatherApi,
    private val mapper: WeatherMapper
) : WeatherRepository {

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