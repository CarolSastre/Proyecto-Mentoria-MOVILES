package com.example.mentoria.di

import com.example.mentoria.core.presentation.SessionManager
import com.example.mentoria.main.presentation.MainViewModel
import com.example.mentoria.main.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { SessionManager() }

    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}