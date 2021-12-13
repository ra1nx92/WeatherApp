package com.example.wheaterapp.data.network.service

import com.example.wheaterapp.data.network.service.model.WeatherModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object WeatherApiFactory {

    private const val BASE_URL = "https://api.openweathermap.org/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val dataService = retrofit.create(WeatherApi::class.java)
}