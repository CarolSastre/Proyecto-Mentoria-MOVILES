package com.example.mentoria.di

import com.example.mentoria.features.auth.data.local.AuthLocalDataSource
import com.example.mentoria.features.auth.data.local.AuthLocalDataSourceDataStoreImpl
import com.example.mentoria.features.auth.data.remote.AuthApi
import com.example.mentoria.features.auth.data.remote.AuthRemoteDataSource
import com.example.mentoria.features.auth.data.remote.AuthRemoteDataSourceImpl
import com.example.mentoria.features.auth.data.repository.AuthRepositoryImpl
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val authModule = module {
    // 1. Proveer AuthApi usando Retrofit
    single<AuthApi> {
        get<Retrofit>().create(AuthApi::class.java)
    }

    // 2. Proveer AuthRemoteDataSource (La implementación REAL, no la Fake)
    single<AuthRemoteDataSource> {
        AuthRemoteDataSourceImpl(api = get())
    }

    // 3. Proveer AuthLocalDataSource (DataStore)
    single<AuthLocalDataSource> {
        AuthLocalDataSourceDataStoreImpl(dataStore = get())
    }

    // 4. Proveer Repositorio
    single<AuthRepository> {
        AuthRepositoryImpl(remote = get(), localDataSource = get())
    }
}
/*
    // Domain
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }

    factoryOf(::LoginUseCase)
    factory { IsUserLoggedInUseCase(get()) }

    // Presentation
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        RegisterViewModel(get())
    }
*/