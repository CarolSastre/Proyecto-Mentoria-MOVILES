package com.example.mentoria.features.auth.data.remote

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.features.auth.data.remote.dto.LoginRequest
import com.example.mentoria.features.auth.data.remote.dto.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/usuarios/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
    @POST("/api/usuarios")
    suspend fun register(@Body usuarioDto: UsuarioDto): Response<Unit>
}