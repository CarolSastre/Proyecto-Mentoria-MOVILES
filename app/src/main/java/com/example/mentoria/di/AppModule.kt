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
import com.example.mentoria.features.auth.data.local.AuthLocalDataSource
import com.example.mentoria.features.auth.data.local.SessionManager
import com.example.mentoria.features.auth.data.remote.AuthApi
import com.example.mentoria.features.auth.data.remote.AuthRemoteDataSource
import com.example.mentoria.features.auth.data.remote.AuthRemoteDataSourceImpl
import com.example.mentoria.features.auth.data.repository.AuthRepositoryImpl
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import com.example.mentoria.features.auth.domain.usecases.IsUserLoggedInUseCase
import com.example.mentoria.features.auth.domain.usecases.LoginUseCase
import com.example.mentoria.features.auth.domain.usecases.LogoutUseCase
import com.example.mentoria.features.auth.domain.usecases.RegisterUseCase
import com.example.mentoria.features.auth.presentation.login.LoginViewModel
import com.example.mentoria.features.auth.presentation.register.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import retrofit2.Retrofit

val appModule = module {
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
    // --- Session Manager (SINGLETON) ---
    // Actúa como AuthLocalDataSource y gestor de sesión
    single { SessionManager(androidContext()) } bind AuthLocalDataSource::class

    // --- APIs (Retrofit Creates) ---
    single<AuthApi> { get<Retrofit>().create(AuthApi::class.java) }
    single<UsuarioApiService> { get<Retrofit>().create(UsuarioApiService::class.java) }

    // --- Data Sources ---
    singleOf(::AuthRemoteDataSourceImpl) { bind<AuthRemoteDataSource>() }

    // --- Repositories (SINGLETONS) ---
    // AuthRepository: Mantiene el usuario en memoria
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }

    // UsuarioRepository
    singleOf(::UsuarioRepositoryRemoteImpl) { bind<UsuarioRepository>() }

    // ==========================================
    // 3. DOMAIN (USE CASES)
    // ==========================================
    factoryOf(::RegisterUseCase)
    factoryOf(::LoginUseCase)
    factoryOf(::LogoutUseCase)
    factoryOf(::IsUserLoggedInUseCase)
    factoryOf(::GetAllUsuariosUseCase)
    factoryOf(::GetUsuarioUseCase)


    // ==========================================
    // 4. PRESENTATION (VIEW MODELS)
    // ==========================================
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::CalendarioViewModel)
    viewModelOf(::HorarioViewModel)

    // ViewModel con parámetros
    viewModel { (usuarioId: String) ->
        UsuarioDetailsViewModel(usuarioId, get())
    }
}
