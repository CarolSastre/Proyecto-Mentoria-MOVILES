package com.example.mentoria.features.auth.data.remote

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.features.auth.data.remote.dto.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    // TODO: cmbiar el endpoint
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): UsuarioDto
}