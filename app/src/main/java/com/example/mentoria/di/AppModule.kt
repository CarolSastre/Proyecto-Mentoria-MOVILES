package com.example.mentoria.di

import com.example.mentoria.core.presentation.SessionManager
import com.example.mentoria.core.presentation.screens.MainViewModel
import com.example.mentoria.core.presentation.screens.calendario.CalendarioViewModel
import com.example.mentoria.core.presentation.screens.home.HomeViewModel
import com.example.mentoria.core.presentation.screens.horario.HorarioViewModel
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import com.example.mentoria.features.auth.domain.usecases.LoginUseCase
import com.example.mentoria.features.auth.domain.usecases.LogoutUseCase
import com.example.mentoria.features.auth.presentation.login.LoginViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { SessionManager() }
    //viewModel { MainViewModel() }

    factoryOf(::LogoutUseCase)
    viewModelOf(::CalendarioViewModel)
    viewModelOf(::HorarioViewModel)
    viewModelOf(::HomeViewModel)
}