package com.example.mentoria.di

import androidx.room.Room
import com.example.mentoria.core.data.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    // Base de Datos (Room)
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "mentoria_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    // DAOs
    single { get<AppDatabase>().usuarioDao() }
}