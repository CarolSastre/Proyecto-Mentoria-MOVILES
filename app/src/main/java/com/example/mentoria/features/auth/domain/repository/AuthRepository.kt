package com.example.mentoria.features.auth.domain.repository

import com.example.mentoria.core.domain.model.Usuario
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(dni: String, password: String): Usuario?
    suspend fun register(dni: String, password: String): Usuario
    suspend fun logout()
    suspend fun isUserLoggedIn(): Boolean
    fun getSessionState(): Flow<Boolean>
}