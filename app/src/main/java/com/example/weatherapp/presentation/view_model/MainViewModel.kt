package com.example.weatherapp.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.*
import com.example.weatherapp.domain.use_cases.GetLoadCityUseCase
import com.example.weatherapp.domain.use_cases.GetSaveCityUseCase
import com.example.weatherapp.domain.use_cases.GetWeatherDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val weatherDataUseCase: GetWeatherDataUseCase,
    private val loadCityUseCase: GetLoadCityUseCase,
    private val saveCityUseCase: GetSaveCityUseCase
) : ViewModel() {

    val weatherData = MutableLiveData<Result>()

    init {
        loadSettings()
    }

    fun refreshData(cityName: String) {
        weatherData.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = weatherDataUseCase.invoke(cityName)
            weatherData.postValue(result)
        }
    }

    fun loadSettings() = loadCityUseCase.invoke()
    fun saveSettings(cityName: String) = saveCityUseCase.invoke(cityName)
}
