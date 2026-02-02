package com.example.mentoria.di

import com.example.mentoria.core.presentation.SessionManager
import com.example.mentoria.core.presentation.screens.MainViewModel
import com.example.mentoria.core.presentation.screens.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { SessionManager() }

    viewModel { MainViewModel() }
    viewModel { HomeViewModel() }
}