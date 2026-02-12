package com.example.mentoria.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.mentoria.core.datastore.dataStore
import com.example.mentoria.features.auth.data.local.AuthLocalDataSource
import com.example.mentoria.features.auth.data.local.AuthLocalDataSourceDataStoreImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {

    single<DataStore<Preferences>> {
        androidContext().dataStore
    }

    single<AuthLocalDataSource> {
        AuthLocalDataSourceDataStoreImpl(get())
    }
}