package com.example.mentoria.core.domain.repositories

import com.example.mentoria.core.domain.model.Usuario

interface UsuariosRepository {
    suspend fun getUsuarios(page: Int = 1): List<Usuario>

    suspend fun getUsuario(query:String, page: Int = 1): List<Usuario>
}