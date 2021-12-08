package com.example.wheaterapp.data.presentation.screen

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.wheaterapp.R
import com.example.wheaterapp.data.presentation.viewModel.MainViewModel
import com.example.wheaterapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var GET: SharedPreferences
    private lateinit var SET: SharedPreferences.Editor

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        SET = GET.edit()

        val cName = GET.getString("cityName", "ankara")
        binding.edtCityName.setText(cName)

        viewModel.refreshData()
        getLiveData()
    }

    private fun getLiveData() {
        viewModel.weatherData.observe(this, { data ->
            data?.let {
                with(binding) {
                    llData.visibility = View.VISIBLE
                    tvDegree.text = data.main.temp.toString()
                    tvCityCode.text = data.sys.country
                    tvCityName.text = data.name
                    tvHumidity.text = data.main.humidity.toString()
                    tvWindSpeed.text = data.wind.speed.toString()
                    tvLat.text = data.coord.lat.toString()
                    tvLon.text = data.coord.lon.toString()

                    Glide.with(this@MainActivity)
                        .load("http://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png")
                        .into(imgWeatherPictures)
                }
            }
        })

        viewModel.weatherLoad.observe(this, { load ->
            load?.let {
                with(binding) {
                    if (it) {
                        pbLoading.visibility = View.VISIBLE
                        tvError.visibility = View.GONE
                        llData.visibility = View.GONE
                    } else {
                        pbLoading.visibility = View.GONE
                    }
                }
            }

        })

        viewModel.weatherError.observe(this, { error ->
            error?.let {
                with(binding) {
                    if (it) {
                        tvError.visibility = View.VISIBLE
                        llData.visibility = View.GONE
                        pbLoading.visibility = View.GONE
                    } else {
                        tvError.visibility = View.GONE
                    }
                }
            }
        })
    }
}