package com.example.weatherapp.presentation.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.domain.Result
import com.example.weatherapp.presentation.view_model.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val cName = sharedPreferencesSetting()

        viewModel.refreshData(cName)
        getLiveData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            swipeRefresh()
        }

        binding.imgSearchCity.setOnClickListener {
            clickSearchCity()
        }
    }

    private fun sharedPreferencesSetting(): String {
        val cName = viewModel.loadSettings().toString()
        binding.edtCityName.setText(cName)
        return cName
    }

    private fun swipeRefresh() {
        with(binding) {
            llData.visibility = View.GONE
            tvError.visibility = View.GONE
            pbLoading.visibility = View.GONE

            val cityName = viewModel.loadSettings()
            edtCityName.setText(cityName)
            viewModel.refreshData(cityName ?: "")
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun clickSearchCity() {
        val cityName = binding.edtCityName.text.toString()
        viewModel.saveSettings(cityName)
        viewModel.refreshData(cityName)
    }

    private fun getLiveData() {
        viewModel.weatherData.observe(this) { data: Result ->
            when (data) {
                is Result.Loading -> {
                    binding.pbLoading.isVisible = true
                }
                is Result.Success -> {
                    with(binding) {
                        pbLoading.isVisible = false
                        llData.visibility = View.VISIBLE
                        tvDegree.text = ("""
                         """ + data.weatherInfo.temp + getString(R.string.celsius) + """
                         """).trimIndent()
                        tvCountryCode.text = data.weatherInfo.country
                        tvCityName.text = data.weatherInfo.name
                        tvHumidity.text = data.weatherInfo.humidity.toString()
                        tvWindSpeed.text = data.weatherInfo.speed.toString()
                        tvFeels.text = data.weatherInfo.feelsLike.toString()
                        tvPress.text = data.weatherInfo.pressure.toString()
                        binding.tvError.isVisible = false

                        Glide.with(this@MainActivity)
                            .load("https://openweathermap.org/img/wn/" + data.weatherInfo.icon + "@2x.png")
                            .into(imgWeatherPictures)
                    }
                }
                is Result.Error -> {
                    binding.pbLoading.isVisible = false
                    binding.tvError.isVisible = true
                    Toast.makeText(
                        this,
                        "Error ${data.exception.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is Result.NetworkError -> {
                    binding.pbLoading.isVisible = false
                    binding.tvError.isVisible = true
                    Toast.makeText(this, "Ошибка подключения к интернету", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}