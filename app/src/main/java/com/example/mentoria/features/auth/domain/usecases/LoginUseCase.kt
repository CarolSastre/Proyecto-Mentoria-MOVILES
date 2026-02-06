package com.example.mentoria.features.auth.domain.usecases

import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.features.auth.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(dni: String, password: String): Usuario? {
        // Validacion
        if (dni.isBlank() || password.isBlank()) {
            throw IllegalArgumentException("Dni o contraseña en blanco")
        }
        // TODO: confirmar toda la info a pedir y validar
        if (dni != "Profesor" || password != "profesor") return null
        else return authRepository.login(dni, password)
    }
}
