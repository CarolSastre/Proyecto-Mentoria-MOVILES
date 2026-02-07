package com.example.mentoria.di

import com.example.mentoria.core.data.remote.FakeUsuariosApiService
import com.example.mentoria.core.data.remote.MentoriaApiService
import org.koin.dsl.module

val mockNetworkModule = module {
    single<MentoriaApiService> { FakeUsuariosApiService() }
}

/*
val networkModule = module {
    single<EventosApiService> {
        get<Retrofit>().create(EventosApiService::class.java)
    }
}
*/