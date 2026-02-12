package com.example.mentoria.di

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
import com.example.mentoria.core.presentation.screens.MainViewModel
import org.koin.android.ext.koin.androidContext

val appModule = module {

    // --- 1. DATASTORE ---
    single { androidContext().dataStore }

    // --- 2. BASE DE DATOS (ROOM) ---
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "mentoria_database"
        )
            .fallbackToDestructiveMigration() // TODO: Útil en desarrollo para no crashear si cambias esquemas, borrar después si eso...
            .build()
    }

    // --- 3. DAOs ---
    single { get<AppDatabase>().usuarioDao() }

    //single { SessionManager(androidContext()) }

    //factoryOf(::LogoutUseCase)
    viewModelOf(::CalendarioViewModel)
    viewModelOf(::HorarioViewModel)
    viewModelOf(::HomeViewModel)
}
/**
// Networking
single { provideOkHttpClient(get()) }
single { provideRetrofit(get()) }
single { provideAuthApi(get()) }
}

fun provideOkHttpClient(sessionManager: SessionManager): OkHttpClient {
return OkHttpClient.Builder()
.addInterceptor(AuthInterceptor(sessionManager))
.build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
return Retrofit.Builder()
.baseUrl("http://10.0.2.2:8080") // Replace with your base URL
.client(okHttpClient)
.addConverterFactory(GsonConverterFactory.create())
.build()
}

fun provideAuthApi(retrofit: Retrofit): AuthApi {
return retrofit.create(AuthApi::class.java)
}
 */