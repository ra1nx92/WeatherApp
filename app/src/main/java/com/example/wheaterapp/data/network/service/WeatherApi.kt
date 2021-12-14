package com.example.wheaterapp.data.network.service

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather?&units=metric&")
    suspend fun getWeatherData(
        @Query("q")cityName:String,
        @Query("appid")apiKey:String = API_KEY,
        @Query("lang")language:String = LANGUAGE
    ):ResponseBody

    companion object{
    private const val API_KEY = "fd47fef64bf5ea7366df3e2b5de136ae"
    private const val LANGUAGE = "ru"
    }
}