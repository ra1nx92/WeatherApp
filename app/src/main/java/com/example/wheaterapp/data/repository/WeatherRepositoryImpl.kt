package com.example.wheaterapp.data.repository

import com.example.wheaterapp.data.maper.WeatherMapper
import com.example.wheaterapp.data.network.service.WeatherApiFactory
import com.example.wheaterapp.data.network.model.WeatherModel
import com.example.wheaterapp.domain.Result
import com.example.wheaterapp.domain.WeatherRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class WeatherRepositoryImpl() : WeatherRepository {
    private val gson = Gson()
    private val type: Type = object:TypeToken<WeatherModel>(){}.type
    private val apiService = WeatherApiFactory.dataService
    private val mapper = WeatherMapper()

    override suspend fun getWeatherData(cityName: String): Result {
        return try {
            val result = apiService.getWeatherData(cityName).string()
            val gsonResult = gson.fromJson<WeatherModel>(result,type)
            Result.Success(mapper.mapApiDataToDomain(gsonResult))
        }catch (e:Exception){
            Result.Error(e)
        }
    }
}