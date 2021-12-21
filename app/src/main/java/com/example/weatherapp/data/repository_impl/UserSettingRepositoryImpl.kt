package com.example.weatherapp.data.repository_impl

import android.content.Context
import android.content.SharedPreferences
import com.example.weatherapp.domain.repository.UserSettingsRepository

class UserSettingRepositoryImpl(context: Context): UserSettingsRepository {
     private val sharedPref: SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    override fun saveCity(cityName: String) {
        sharedPref.edit().putString(CITY_KEY, cityName).apply()
    }

    override fun loadCity(): String? {
        return sharedPref.getString(CITY_KEY,"")
    }
    companion object{
       private const val CITY_KEY = "city"
    }
}