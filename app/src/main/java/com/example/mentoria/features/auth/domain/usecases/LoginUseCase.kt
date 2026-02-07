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
        if (dni != "test" || password != "1234")
            throw IllegalArgumentException("Dni o contraseña incorrectos")
        else return Usuario(
            dni = "12345678A",
            nombre = "test",
            apellidos = "test",
            rol = "ALUMNO",
            password = "1234",
            nfc = null
        )
        //else return authRepository.login(dni, password)
    }
}
