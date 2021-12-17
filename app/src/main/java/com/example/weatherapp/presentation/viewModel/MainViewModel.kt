package com.example.wheaterapp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.GetWeatherDataUseCase
import com.example.weatherapp.domain.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.weatherapp.domain.Result

class MainViewModel(private val weatherRepository : WeatherRepository) : ViewModel() {

    val weatherData = MutableLiveData<Result>()
    private val useCaseData = GetWeatherDataUseCase(weatherRepository)

    fun refreshData(cityName: String) {
        weatherData.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = useCaseData.invoke(cityName)
            weatherData.postValue(result)
        }
    }
}
