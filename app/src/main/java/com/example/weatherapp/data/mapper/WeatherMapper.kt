package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.network.model.WeatherModel
import com.example.weatherapp.domain.WeatherInfo

interface WeatherMapper {

    fun map(apiData: WeatherModel): WeatherInfo
    fun convertPressure(pressure:Int):Int

    class Base : WeatherMapper {
        override fun map(apiData: WeatherModel): WeatherInfo {
           return WeatherInfo(
                temp = apiData.main.temp,
                country = apiData.sys.country,
                name = apiData.name,
                humidity = apiData.main.humidity,
                speed = apiData.wind.speed,
                feelsLike = apiData.main.feelsLike,
                pressure = convertPressure(apiData.main.pressure),
                icon = apiData.weather[0].icon
            )
        }

        override fun convertPressure(pressure: Int): Int {
            val mmRs = pressure * COEFFICIENT
            return mmRs.toInt()
        }

        companion object{
           private const val COEFFICIENT = 0.750064
        }
    }
}
