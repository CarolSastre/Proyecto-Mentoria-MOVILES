package com.example.mentoria.core.domain.repositories

import com.example.mentoria.core.domain.model.Usuario
import kotlinx.coroutines.flow.Flow

interface UsuarioRepository {
    fun getAllUsuarios(): Flow<List<Usuario>>

    fun getUsuarioById(id: Int): Flow<Usuario>
}