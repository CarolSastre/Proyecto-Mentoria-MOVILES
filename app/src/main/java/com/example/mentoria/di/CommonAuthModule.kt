package com.example.mentoria.di

import com.example.mentoria.features.auth.domain.usecases.IsUserLoggedInUseCase
import com.example.mentoria.features.auth.domain.usecases.LoginUseCase
import com.example.mentoria.features.auth.domain.usecases.LogoutUseCase
import com.example.mentoria.features.auth.domain.usecases.RegisterUseCase
import com.example.mentoria.features.auth.presentation.login.LoginViewModel
import com.example.mentoria.features.auth.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val commonAuthModule = module {
    // Domain
    factoryOf(::RegisterUseCase)
    factoryOf(::LoginUseCase)
    factoryOf(::LogoutUseCase)
    factoryOf(::IsUserLoggedInUseCase)
    // Presentation
    //viewModelOf(::RegisterViewModel)
    //viewModelOf(::LoginViewModel)
    viewModel{
        LoginViewModel(get())
    }
    viewModel {
        RegisterViewModel(get())
    }
}
