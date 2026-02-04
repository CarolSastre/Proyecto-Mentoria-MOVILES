package com.example.mentoria.di

import com.example.mentoria.core.data.remote.FakeRegistrosApiService
import com.example.mentoria.core.data.remote.FakeUsuariosApiService
import com.example.mentoria.core.data.remote.UsuarioApiService
import com.example.mentoria.core.data.repositories.DataStoreRepositoryImpl
import com.example.mentoria.core.data.repositories.UsuariosRepositoryRemoteImpl
import com.example.mentoria.core.datastore.dataStore
import com.example.mentoria.core.domain.repositories.DataStoreRepository
import com.example.mentoria.core.domain.repositories.UsuariosRepository
import com.example.mentoria.core.presentation.screens.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module {
    //single{ SessionManager() }
    single<DataStoreRepository> { DataStoreRepositoryImpl(get()) }
    single<UsuariosRepository> { UsuariosRepositoryRemoteImpl(
        get(),
        usuarioDao = get()
    ) }
    single { androidContext().dataStore }
    viewModel { HomeViewModel(get()) }
}