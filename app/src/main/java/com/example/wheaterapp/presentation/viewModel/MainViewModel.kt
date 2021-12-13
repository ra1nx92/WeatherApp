package com.example.wheaterapp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wheaterapp.data.network.service.model.WeatherModel
import com.example.wheaterapp.data.network.service.WeatherApiFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    val weatherData = MutableLiveData<WeatherModel>()
    val weatherError = MutableLiveData<Boolean>()
    val weatherLoad = MutableLiveData<Boolean>()

    fun refreshData(cityName:String) {
        getDataFromApi(cityName)
    }


    private fun getDataFromApi(cityName:String) {
        weatherLoad.value = true
        disposable.add(
            WeatherApiFactory.getDataService(cityName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherModel>() {
                    override fun onSuccess(t: WeatherModel) {
                        weatherData.value = t
                        weatherError.value = false
                    }

                    override fun onError(e: Throwable) {
                        weatherError.value = true
                        weatherLoad.value = false
                    }
                })
        )
    }
}