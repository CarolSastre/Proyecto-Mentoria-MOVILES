package com.example.mentoria.features.auth.data.remote

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.features.auth.data.remote.dto.LoginRequest
import com.example.mentoria.features.auth.data.remote.dto.LoginResponse

class AuthRemoteDataSourceImpl(
    private val api: AuthApi // Retrofit se inyecta aquí
) : AuthRemoteDataSource {

    override suspend fun login(request: LoginRequest): LoginResponse {
        val response = api.login(request)
        println("Response: $response")
        return response.body() ?: throw Exception("Error al obtener el usuario")
    }

    override suspend fun register(usuarioDto: UsuarioDto): Unit {
        return api.register(usuarioDto).body() ?: throw Exception("Error al registrar")
    }
}