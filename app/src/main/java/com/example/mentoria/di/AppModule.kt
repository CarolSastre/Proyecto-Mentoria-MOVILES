package com.example.mentoria.di

import androidx.room.Room
import com.example.mentoria.core.data.remote.FakeUsuariosApiService
import com.example.mentoria.core.datastore.AppDatabase
import com.example.mentoria.core.presentation.SessionManager
import com.example.mentoria.core.presentation.screens.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // 1. Crear la Base de Datos (Room)
    single {
        Room.databaseBuilder(
            androidContext(), // Koin nos da el contexto automáticamente
            AppDatabase::class.java,
            "mentoria_database" // Nombre del archivo físico
        ).build()
    }

    // 2. Crear el DAO (Extraerlo de la base de datos para usarlo fácil)
    single {
        get<AppDatabase>().usuarioDao()
    }

    // Tus otras dependencias...
    // single { SessionManager() }
    single { FakeUsuariosApiService() }

    viewModel { HomeViewModel() }
}