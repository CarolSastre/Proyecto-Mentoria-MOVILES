package com.example.mentoria.features.auth.domain.usecases

import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.features.auth.domain.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) { // TODO: confirmar tda la info a padir
    suspend operator fun invoke(dni: String, password: String): Usuario {
        return Usuario(
            dni = "12345678A",
            nombre = "test",
            apellidos = "test",
            rol = "ALUMNO",
            password = "1234",
            nfc = null
        )
        //return repository.register(dni, password)
    }
}
