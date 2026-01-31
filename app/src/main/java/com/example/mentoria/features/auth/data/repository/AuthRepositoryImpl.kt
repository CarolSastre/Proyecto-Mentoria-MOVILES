package com.example.mentoria.features.auth.data.repository

import com.example.mentoria.core.data.remote.mappers.toDomain
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.features.auth.data.remote.AuthApi
import com.example.mentoria.features.auth.data.remote.dto.LoginRequest
import com.example.mentoria.features.auth.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthApi
): AuthRepository {
    override suspend fun login(dni: String, password: String): Usuario {
        return api.login(LoginRequest(dni, password)).toDomain()
    }

    override suspend fun register(
        dni: String,
        password: String,
        nombre: String,
        apellidos: String,
        email:String,
        fechaNacimiento: String,
        rol: Rol
    ): Usuario {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun isUserLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }

}