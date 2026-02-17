package com.example.mentoria.di

import com.example.mentoria.core.data.remote.UsuarioApiService
import com.example.mentoria.core.presentation.screens.calendario.CalendarioViewModel
import com.example.mentoria.core.presentation.screens.home.HomeViewModel
import com.example.mentoria.core.presentation.screens.horario.HorarioViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import androidx.room.Room
import com.example.mentoria.core.data.local.AppDatabase
import com.example.mentoria.core.data.local.dataStore
import com.example.mentoria.core.data.repositories.UsuarioRepositoryRemoteImpl
import com.example.mentoria.core.domain.repositories.UsuarioRepository
import com.example.mentoria.core.domain.usecase.GetAllUsuariosUseCase
import com.example.mentoria.core.domain.usecase.GetUsuarioUseCase
import com.example.mentoria.core.presentation.screens.MainViewModel
import com.example.mentoria.core.presentation.screens.search.SearchViewModel
import com.example.mentoria.core.presentation.screens.usuariodetails.UsuarioDetailsViewModel
import com.example.mentoria.features.auth.data.local.SessionManager
import com.example.mentoria.features.auth.domain.usecases.LogoutUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import retrofit2.Retrofit

val appModule = module {
    // Data -
    // --- 1. DATASTORE ---
    single { androidContext().dataStore }
    single { SessionManager(androidContext()) }

    // --- 2. BASE DE DATOS (ROOM) ---
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "mentoria_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    // --- 3. DAOs ---
    single { get<AppDatabase>().usuarioDao() }
    single<UsuarioApiService> {
        get<Retrofit>().create(UsuarioApiService::class.java)
    }
    single<UsuarioRepository> {
        UsuarioRepositoryRemoteImpl(get(), get())
    }
    // singleOf(::UsuarioRepositoryRemoteImpl) bind UsuarioRepository::class

    // Domain
    factoryOf(::GetAllUsuariosUseCase)
    factoryOf(::GetUsuarioUseCase)
    factoryOf(::LogoutUseCase)

    // Presentation
    viewModel{
        SearchViewModel(get())
    }
    viewModel {
        (usuarioId: String) ->
        UsuarioDetailsViewModel(usuarioId, get())
    }
    viewModelOf(::CalendarioViewModel)
    viewModelOf(::HorarioViewModel)
    viewModel{
        HomeViewModel(get(), get())
    }
    viewModel { MainViewModel(get()) }
}
