package com.example.mentoria.di

/*
import com.example.mentoria.features.auth.domain.usecases.IsUserLoggedInUseCase
import com.example.mentoria.features.auth.domain.usecases.LoginUseCase
import com.example.mentoria.features.auth.domain.usecases.RegisterUseCase
import com.example.mentoria.features.auth.presentation.login.LoginViewModel
import com.example.mentoria.features.auth.presentation.register.RegisterViewModel
 */
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authRepositoryModule = module {
    // Data
    single {
        // AuthRemoteDataSource(get())
    }

    /* TODO: descomentar cuando esté el repo de autentificación
    single<AuthRepository> {
        AuthRepositoryImpl(
            remote = get()
            local = get()
        )
    }
    */
}

/*
val commonAuthModule = module {
    // Domain
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factoryOf(::LoginUseCase)
    factory { IsUserLoggedInUseCase(get()) }

    // Presentation
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        RegisterViewModel(get())
    }
}
*/