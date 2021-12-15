package com.example.WeatherApp.data.repository

import com.example.WeatherApp.data.maper.WeatherMapper
import com.example.WeatherApp.data.network.service.WeatherApiFactory
import com.example.WeatherApp.data.network.model.WeatherModel
import com.example.WeatherApp.domain.Result
import com.example.WeatherApp.domain.WeatherRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception
import java.lang.reflect.Type
import java.net.ConnectException
import java.net.UnknownHostException

class WeatherRepositoryImpl() : WeatherRepository {
    private val gson = Gson()
    private val type: Type = object : TypeToken<WeatherModel>() {}.type
    private val apiService = WeatherApiFactory.dataService
    private val mapper = WeatherMapper()

    override suspend fun getWeatherData(cityName: String): Result {
        return try {
            val result = apiService.getWeatherData(cityName).string()
            val gsonResult = gson.fromJson<WeatherModel>(result, type)
            Result.Success(mapper.mapApiDataToDomain(gsonResult))
        } catch (e: UnknownHostException) {
            Result.NetworkError(e)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}