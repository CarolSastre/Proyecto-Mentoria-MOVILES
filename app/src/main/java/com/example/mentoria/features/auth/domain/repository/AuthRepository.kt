package com.example.mentoria.features.auth.domain.repository

import com.example.mentoria.features.auth.domain.model.Usuario

interface AuthRepository {
    suspend fun login(email: String, password: String): Usuario
    suspend fun register(email: String, password: String): Usuario
    suspend fun logout()
    suspend fun isUserLoggedIn(): Boolean
}