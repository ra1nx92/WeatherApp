package com.example.weatherapp.di

import com.example.weatherapp.data.network.service.WeatherApi
import com.example.weatherapp.data.network.service.WeatherApiFactory
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.domain.WeatherDomainMapper
import org.koin.dsl.module

val weathersModel = module {
    fun provideApi(): WeatherApi {
        return WeatherApiFactory.dataService
    }

    fun provideMapper(): WeatherDomainMapper {
        return WeatherDomainMapper.Base()
    }

    fun provideRepository(api: WeatherApi, mapper: WeatherDomainMapper): WeatherRepository {
        return WeatherRepositoryImpl(api, mapper)
    }

    single<WeatherApi> { provideApi() }
    single<WeatherDomainMapper> { provideMapper() }
    single<WeatherRepository> { provideRepository(get(), get()) }
}