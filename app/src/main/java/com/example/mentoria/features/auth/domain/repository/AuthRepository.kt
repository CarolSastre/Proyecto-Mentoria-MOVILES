package com.example.mentoria.features.auth.domain.repository

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.domain.model.Usuario
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val currentUser: StateFlow<Usuario?>
    suspend fun fetchCurrentUser()
    suspend fun login(dni: String, password: String): Usuario?
    suspend fun register(usuarioDto: UsuarioDto): Unit

    suspend fun logout()
    fun getSessionState(): Flow<Boolean>
}