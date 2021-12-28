package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.data.network.service.WeatherApi
import com.example.weatherapp.data.network.service.WeatherApiFactory
import com.example.weatherapp.data.repository_impl.UserSettingRepositoryImpl
import com.example.weatherapp.data.repository_impl.WeatherRepositoryImpl
import com.example.weatherapp.domain.repository.UserSettingsRepository
import com.example.weatherapp.data.mapper.WeatherMapper
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.use_cases.GetLoadCityUseCase
import com.example.weatherapp.domain.use_cases.GetSaveCityUseCase
import com.example.weatherapp.domain.use_cases.GetWeatherDataUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val applicationModule = module {
    fun provideApi(): WeatherApi {
        return WeatherApiFactory.dataService
    }
    fun provideMapper(): WeatherMapper {
        return WeatherMapper.Base()
    }
    fun provideRepository(api: WeatherApi, mapper: WeatherMapper): WeatherRepository {
        return WeatherRepositoryImpl(api, mapper)
    }
    fun provideSettingRepository(context: Context): UserSettingsRepository {
        return UserSettingRepositoryImpl(context)
    }
    fun provideLoadCityUseCase(repository: UserSettingsRepository):GetLoadCityUseCase{
        return GetLoadCityUseCase(repository)
    }
    fun provideSaveCityUseCase(repository: UserSettingsRepository):GetSaveCityUseCase{
        return GetSaveCityUseCase(repository)
    }
    fun provideWeatherDataUseCase(repository: WeatherRepository):GetWeatherDataUseCase{
        return GetWeatherDataUseCase(repository)
    }


    single<WeatherApi> { provideApi() }
    single<WeatherMapper> { provideMapper() }
    single<WeatherRepository> { provideRepository(get(), get()) }
    single<UserSettingsRepository> { provideSettingRepository(androidContext()) }
    single<GetLoadCityUseCase> { provideLoadCityUseCase(get()) }
    single<GetSaveCityUseCase> { provideSaveCityUseCase(get()) }
    single<GetWeatherDataUseCase> { provideWeatherDataUseCase(get()) }
}