package com.example.mentoria.di

import com.example.mentoria.core.presentation.SessionManager
import com.example.mentoria.core.presentation.screens.calendario.CalendarioViewModel
import com.example.mentoria.core.presentation.screens.home.HomeViewModel
import com.example.mentoria.core.presentation.screens.horario.HorarioViewModel
import com.example.mentoria.features.auth.domain.usecases.LogoutUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import androidx.room.Room
import com.example.mentoria.core.datastore.AppDatabase
import com.example.mentoria.core.datastore.dataStore // Importa la extensión del paso 1
import org.koin.android.ext.koin.androidContext


val appModule = module {

    // --- 1. DATASTORE (Esto es lo que faltaba y provocaba el crash) ---
    single { androidContext().dataStore }

    // --- 2. BASE DE DATOS (ROOM) ---
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "mentoria_database"
        )
            .fallbackToDestructiveMigration() // Útil en desarrollo para no crashear si cambias esquemas
            .build()
    }

    // --- 3. DAOs ---
    single { get<AppDatabase>().usuarioDao() }

    single { SessionManager() }
    //viewModel { MainViewModel() }

    factoryOf(::LogoutUseCase)
    viewModelOf(::CalendarioViewModel)
    viewModelOf(::HorarioViewModel)
    viewModelOf(::HomeViewModel)
}