package com.example.mentoria.core.data.repositories

import com.example.mentoria.core.data.remote.UsuarioApiService
import com.example.mentoria.core.data.remote.mappers.toDomain
import com.example.mentoria.core.data.remote.mappers.toEntity
import com.example.mentoria.core.datastore.dao.UsuarioDao
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.domain.repositories.UsuariosRepository

class UsuariosRepositoryRemoteImpl(
    private val api: UsuarioApiService,
    private val usuarioDao: UsuarioDao
): UsuariosRepository {
    override suspend fun getUsuarios(): List<Usuario> {
        // cargar datos del Room si existen
        val local = usuarioDao.getUsuarios()
        if (local.isNotEmpty()) {
            return local.map { it.toDomain() }
        }
        // cargar datos de la API
        val remote = api.getUsuarios()
        // guardar datos en Room
        usuarioDao.insertAll(remote.map { it.toEntity() })
        return remote.map { it.toDomain() }
    }

    override suspend fun getUsuario(
        query: String
    ): List<Usuario> {
        TODO("Not yet implemented")
    }

}