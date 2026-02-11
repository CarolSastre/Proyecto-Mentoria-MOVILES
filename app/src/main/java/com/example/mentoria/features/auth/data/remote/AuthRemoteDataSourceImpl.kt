package com.example.mentoria.features.auth.data.remote

import com.example.mentoria.features.auth.data.remote.dto.LoginRequest
import com.example.mentoria.features.auth.data.remote.dto.LoginResponse

class AuthRemoteDataSourceImpl(
    private val api: AuthApi // Retrofit se inyecta aquí
) : AuthRemoteDataSource {

    override suspend fun login(request: LoginRequest): LoginResponse {
        // Aquí se hace la llamada de red real
        return api.login(request)
    }
}