package com.example.mentoria.core.domain.repositories

import com.example.mentoria.core.domain.model.Usuario

interface UsuariosRepository {
    suspend fun getUsuarios(): List<Usuario>

    suspend fun getUsuario(query:String): List<Usuario>
}