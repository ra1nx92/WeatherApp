package com.example.wheaterapp.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiFactory {

    const val BASE_URL = "api.openweathermap.org/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}