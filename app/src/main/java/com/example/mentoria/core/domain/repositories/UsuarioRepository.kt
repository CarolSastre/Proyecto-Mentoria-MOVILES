package com.example.mentoria.core.domain.repositories

import com.example.mentoria.core.domain.model.Usuario
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface UsuarioRepository {
    fun getAllUsuarios(): Flow<List<Usuario>>

    fun getUsuarioById(id: String): Flow<Usuario>
}