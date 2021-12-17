package com.example.wheaterapp.di

import com.example.wheaterapp.data.network.service.WeatherApi
import com.example.wheaterapp.data.network.service.WeatherApiFactory
import com.example.wheaterapp.data.repository.WeatherRepositoryImpl
import com.example.wheaterapp.domain.WeatherDomainMapper
import com.example.wheaterapp.domain.WeatherRepository
import org.koin.dsl.module
import kotlin.math.sin

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