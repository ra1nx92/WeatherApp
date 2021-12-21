package com.example.weatherapp.di

import com.example.weatherapp.presentation.view_model.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val screenModule = module {
    viewModel <MainViewModel> { MainViewModel(get(), get(),get()) }
}