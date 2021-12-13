package com.example.wheaterapp.presentation.screen

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.wheaterapp.presentation.viewModel.MainViewModel
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

        viewModel.refreshData(cName ?: "")
        getLiveData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            swipeRefresh(cName)
        }

        binding.imgSearchCity.setOnClickListener {
            clickSearchCity()
        }
    }

    private fun swipeRefresh(cName: String?) {
        with(binding) {
            llData.visibility = View.GONE
            tvError.visibility = View.GONE
            pbLoading.visibility = View.GONE

            val cityName = GET.getString("cityName", cName)
            edtCityName.setText(cityName)
            viewModel.refreshData(cityName ?: "")
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun clickSearchCity() {
        val cityName = binding.edtCityName.text.toString()
        SET.putString("cityName", cityName)
        SET.commit()
        viewModel.refreshData(cityName)
        getLiveData()
    }

    private fun getLiveData() {
        viewModel.weatherData.observe(this, { data ->
            data?.let {
                with(binding) {
                    llData.visibility = View.VISIBLE
                    pbLoading.visibility = View.GONE
                    tvDegree.text = data.main.temp.toString()
                    tvCountryCode.text = data.sys.country
                    tvCityName.text = data.name
                    tvHumidity.text = data.main.humidity.toString()
                    tvWindSpeed.text = data.wind.speed.toString()
                    tvLat.text = data.coord.lat.toString()
                    tvLon.text = data.coord.lon.toString()

                    Glide.with(this@MainActivity)
                        .load("https://openweathermap.org/img/wn/" + data.weather[0].icon + "@2x.png")
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