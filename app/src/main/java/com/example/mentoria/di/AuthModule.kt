package com.example.mentoria.di

import com.example.mentoria.core.data.remote.UsuarioApiService
import com.example.mentoria.features.auth.data.local.AuthLocalDataSource
import com.example.mentoria.features.auth.data.local.AuthLocalDataSourceDataStoreImpl
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
    single { SessionManager(androidContext()) } bind AuthLocalDataSource::class

    // 1. Proveer AuthApi usando Retrofit
    single<AuthApi> { get<Retrofit>().create(AuthApi::class.java) }
    single<UsuarioApiService> { get<Retrofit>().create(UsuarioApiService::class.java) }

    // 2. Proveer AuthRemoteDataSource
    singleOf(::AuthRemoteDataSourceImpl) { bind<AuthRemoteDataSource>() }
    // 3. Proveer AuthLocalDataSource (DataStore)
    singleOf(::AuthLocalDataSourceDataStoreImpl) { bind<AuthLocalDataSourceDataStoreImpl>() }

    // Y para el repositorio
    single<AuthRepository> {
        AuthRepositoryImpl(get(), get(), get())
    }

    // Domain
    factoryOf(::RegisterUseCase)
    factoryOf(::LoginUseCase)
    factoryOf(::LogoutUseCase)
    factoryOf(::IsUserLoggedInUseCase)
    // Presentation
    viewModel { RegisterViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}