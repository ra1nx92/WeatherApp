package com.example.weatherapp.data.network.service


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


object WeatherApiFactory {

    private const val BASE_URL = "https://api.openweathermap.org/"

    val dataService: WeatherApi = initRetrofit().create(WeatherApi::class.java)

    private fun initRetrofit():Retrofit {
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }
}