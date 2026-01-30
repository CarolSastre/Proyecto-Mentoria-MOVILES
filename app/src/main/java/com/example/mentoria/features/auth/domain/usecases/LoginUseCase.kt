package com.example.mentoria.features.auth.domain.usecases

import com.example.mentoria.features.auth.domain.model.Usuario
import com.example.mentoria.features.auth.domain.repository.AuthRepository

class LoginUseCase( // TODO: confirmar tda la info a pedir
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(dni: String, password: String): Usuario {
        // Validacion
        if (dni.isBlank() || password.isBlank()) {
            throw IllegalArgumentException("Dni o contraseña en blanco")
        }
        return authRepository.login(dni, password)
    }
}
