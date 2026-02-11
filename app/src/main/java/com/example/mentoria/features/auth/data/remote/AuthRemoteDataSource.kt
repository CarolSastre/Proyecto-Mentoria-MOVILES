package com.example.mentoria.features.auth.data.remote

import com.example.mentoria.features.auth.data.remote.dto.LoginRequest
import com.example.mentoria.features.auth.data.remote.dto.LoginResponse

interface AuthRemoteDataSource {
    suspend fun login(request: LoginRequest): LoginResponse
}