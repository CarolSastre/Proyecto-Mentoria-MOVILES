package com.example.mentoria.core.data.repositories

import com.example.mentoria.core.data.MockDataProvider
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.domain.repositories.UsuarioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeUsuarioRepositoryImpl : UsuarioRepository {
    override fun getAllUsuarios(): Flow<List<Usuario>> {
        return flowOf(
            MockDataProvider.usuarios
        )
    }

    override fun getUsuarioById(id: String): Flow<Usuario> {
        return flowOf(
            MockDataProvider.usuarios.find { it.id == id }!!
        )
    }
}