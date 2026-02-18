package com.example.mentoria.features.auth.domain.usecases

import com.example.mentoria.core.data.remote.mappers.toDomain
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.features.auth.data.remote.AuthRemoteDataSourceImpl
import com.example.mentoria.features.auth.data.remote.dto.LoginRequest
import com.example.mentoria.features.auth.data.repository.AuthRepositoryImpl

class LoginUseCase(
    private val authRepository: AuthRepositoryImpl
) {
    suspend operator fun invoke(dni: String, password: String): Usuario {
        // Validacion
        if (dni.isBlank() || password.isBlank()) {
            throw IllegalArgumentException("Dni o contraseña en blanco")
        }
        //val result = authRepository.login(LoginRequest(dni, password)).usuario
        val usuario = authRepository.login(dni, password)

        //return result?.toDomain() ?: throw Exception("Dni o contraseña incorrecto")
        return usuario ?: throw Exception("Dni o contraseña incorrecto")
    }
}
