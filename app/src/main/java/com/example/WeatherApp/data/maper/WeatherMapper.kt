package com.example.WeatherApp.data.maper

import com.example.WeatherApp.data.network.model.WeatherModel
import com.example.WeatherApp.domain.WeatherInfo

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