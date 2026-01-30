package com.example.mentoria.core.domain.repositories

import com.example.mentoria.core.domain.model.Usuario

interface UsuariosRepository {
    suspend fun fetchUsuarios(page: Int = 1): List<Usuario>

    suspend fun fetchProfesores(page: Int = 1): List<Usuario>

    suspend fun fetchUsuarios(query:String, page: Int = 1): List<Usuario>
}