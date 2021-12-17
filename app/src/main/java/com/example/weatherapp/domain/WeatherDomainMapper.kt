package com.example.weatherapp.domain

import com.example.weatherapp.data.network.model.WeatherModel

interface WeatherDomainMapper {

    fun map(apiData: WeatherModel): WeatherInfo

    class Base : WeatherDomainMapper {
        override fun map(apiData: WeatherModel): WeatherInfo {
           return WeatherInfo(
                temp = apiData.main.temp,
                country = apiData.sys.country,
                name = apiData.name,
                humidity = apiData.main.humidity,
                speed = apiData.wind.speed,
                lat = apiData.coord.lat,
                lon = apiData.coord.lon,
                icon = apiData.weather[0].icon
            )
        }
    }
}
