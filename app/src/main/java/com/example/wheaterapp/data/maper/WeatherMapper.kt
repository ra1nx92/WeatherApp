package com.example.wheaterapp.data.maper

import com.example.wheaterapp.data.network.service.model.WeatherModel
import com.example.wheaterapp.domain.WeatherInfo

class WeatherMapper {
    fun mapApiDataToDomain(apiData: WeatherModel): WeatherInfo = WeatherInfo(
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