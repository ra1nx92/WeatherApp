package com.example.WeatherApp.presentation.screen

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.WeatherApp.databinding.ActivityMainBinding
import com.example.WeatherApp.domain.Result
import com.example.WeatherApp.presentation.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var GET: SharedPreferences
    private lateinit var SET: SharedPreferences.Editor
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val cName = sharedPreferencesSetting()

        viewModel.refreshData(cName ?: "")
        getLiveData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            swipeRefresh(cName)
        }

        binding.imgSearchCity.setOnClickListener {
            clickSearchCity()
        }
    }

    private fun sharedPreferencesSetting(): String? {
        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        SET = GET.edit()

        val cName = GET.getString("cityName", "moscow")
        binding.edtCityName.setText(cName)
        return cName
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
    }

    private fun getLiveData() {
        viewModel.weatherData.observe(this, { data: Result ->
            when (data) {
                is Result.Loading -> {
                    binding.pbLoading.isVisible = true
                    binding.llData.isVisible = false
                    binding.llSearch.isVisible = false
                }
                is Result.Success -> {
                    with(binding) {
                        llSearch.isVisible = true
                        pbLoading.isVisible = false
                        llData.isVisible = true
                        tvDegree.text = data.weatherInfo.temp.toString()
                        tvCountryCode.text = data.weatherInfo.country
                        tvCityName.text = data.weatherInfo.name
                        tvHumidity.text = data.weatherInfo.humidity.toString()
                        tvWindSpeed.text = data.weatherInfo.speed.toString()
                        tvLat.text = data.weatherInfo.lat.toString()
                        tvLon.text = data.weatherInfo.lon.toString()
                        tvError.isVisible = false

                        Glide.with(this@MainActivity)
                            .load("https://openweathermap.org/img/wn/" + data.weatherInfo.icon + "@2x.png")
                            .into(imgWeatherPictures)
                    }
                }
                is Result.Error -> {
                    binding.llSearch.isVisible = false
                    binding.pbLoading.isVisible = false
                    binding.tvError.isVisible = true
                    Toast.makeText(
                        this,
                        "Error ${data.exception.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }
}