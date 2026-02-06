package com.example.mentoria.features.auth.domain.usecases

import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.features.auth.domain.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) { // TODO: confirmar tda la info a padir
    suspend operator fun invoke(dni: String, password: String): Usuario {
        return repository.register(dni, password)
    }
}
