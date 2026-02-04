package com.example.mentoria.di

import com.example.mentoria.core.data.remote.FakeRegistrosApiService
import com.example.mentoria.core.data.remote.FakeUsuariosApiService
import com.example.mentoria.core.data.remote.RegistroApiService
import com.example.mentoria.core.data.remote.UsuarioApiService
import org.koin.dsl.module

val mockNetworkModule = module {
    single<UsuarioApiService> { FakeUsuariosApiService() }
    single<RegistroApiService> { FakeRegistrosApiService() }
}

/*
val networkModule = module {
    single<EventosApiService> {
        get<Retrofit>().create(EventosApiService::class.java)
    }
}
*/