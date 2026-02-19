package com.example.mentoria.features.auth.data.remote

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.features.auth.data.remote.dto.LoginRequest
import com.example.mentoria.features.auth.data.remote.dto.LoginResponse
import com.example.mentoria.features.auth.data.remote.dto.RegisterResponse

interface AuthRemoteDataSource {
    suspend fun login(request: LoginRequest): LoginResponse
    suspend fun register(usuarioDto: UsuarioDto): RegisterResponse
}