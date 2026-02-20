package com.example.mentoria.di

import com.example.mentoria.features.auth.data.local.AuthLocalDataSource
import com.example.mentoria.features.auth.data.remote.AuthApi
import com.example.mentoria.features.auth.data.remote.AuthRemoteDataSource
import com.example.mentoria.features.auth.data.remote.AuthRemoteDataSourceImpl
import com.example.mentoria.features.auth.data.repository.AuthRepositoryImpl
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import com.example.mentoria.features.auth.domain.usecases.IsUserLoggedInUseCase
import com.example.mentoria.features.auth.domain.usecases.LoginUseCase
import com.example.mentoria.features.auth.domain.usecases.LogoutUseCase
import com.example.mentoria.features.auth.domain.usecases.RegisterUseCase
import com.example.mentoria.features.auth.data.local.SessionManager
import com.example.mentoria.features.auth.presentation.login.LoginViewModel
import com.example.mentoria.features.auth.presentation.register.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val authModule = module {
    // --- Session Manager (SINGLETON) ---
    single<AuthLocalDataSource> { SessionManager(androidContext()) }

    // --- APIs (Retrofit Creates) ---
    single<AuthApi> { get<Retrofit>().create(AuthApi::class.java) }

    // --- Data Sources ---
    singleOf(::AuthRemoteDataSourceImpl) { bind<AuthRemoteDataSource>() }

    // --- Repositories (SINGLETONS) ---
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }

    // --- DOMAIN (USE CASES) ---
    factoryOf(::RegisterUseCase)
    factoryOf(::LoginUseCase)
    factoryOf(::LogoutUseCase)
    factoryOf(::IsUserLoggedInUseCase)

    // --- PRESENTATION (VIEW MODELS) ---
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}