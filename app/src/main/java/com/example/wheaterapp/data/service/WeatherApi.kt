package com.example.wheaterapp.data.service

import com.example.wheaterapp.data.model.WeatherModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface WeatherApi {
    @GET("data/2.5/weather?q=izmir&appid=fd47fef64bf5ea7366df3e2b5de136ae")
    fun getWeatherData():Single<WeatherModel>
}