package com.example.WeatherApp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.WeatherApp.data.repository.WeatherRepositoryImpl
import com.example.WeatherApp.domain.GetWeatherDataUseCase
import com.example.WeatherApp.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
    private val weatherRepository = WeatherRepositoryImpl()
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
