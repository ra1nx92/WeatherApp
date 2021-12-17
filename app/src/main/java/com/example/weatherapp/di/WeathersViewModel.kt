package com.example.weatherapp.di

import com.example.wheaterapp.presentation.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherViewModule = module {
    viewModel <MainViewModel> { MainViewModel(get()) }
}