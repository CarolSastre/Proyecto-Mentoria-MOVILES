package com.example.mentoria.core.data.repositories

import com.example.mentoria.core.data.local.dao.UsuarioDao
import com.example.mentoria.core.data.remote.UsuarioApiService
import com.example.mentoria.core.data.remote.mappers.toDomain
import com.example.mentoria.core.data.remote.mappers.toEntity
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.domain.repositories.UsuarioRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class UsuarioRepositoryRemoteImpl(
    private val dao: UsuarioDao, // local
    private val api: UsuarioApiService, // remote
) : UsuarioRepository {
    override fun getAllUsuarios(): Flow<List<Usuario>> = channelFlow {
        // 1. Inicia una corrutina para escuchar los cambios de la base de datos y emitirlos.
        // Esto emitirá primero los datos en caché.
        val job = launch {
            dao.getUsuarios().map { entities ->
                entities.map { it.toDomain() }
            }.collect { users ->
                send(users)
            }
        }

        // 2. Obtiene los datos de la API y actualiza la base de datos local.
        try {
            val remoteUsers = api.getAllUsuarios().first()
            dao.insertAll(
                usuarios = remoteUsers.map { it.toEntity() }
            )
        } catch (e: Exception) {
            // Maneja las excepciones de red, por ejemplo, registrándolas.
            // El flujo continuará sirviendo datos en caché.
        }

        // 3. Mantiene el canal abierto hasta que el colector cancele el flujo.
        awaitClose { job.cancel() }
    }

    override fun getUsuarioById(id: Int): Flow<Usuario> {
        val local = dao.getUsuarioById(id).map {
            return@map it.toDomain()
        }
        return api.getUsuarioById(id).onEach {
            dao.insert(it.toEntity())
        }.map {
            it.toDomain()
        }
    }

}
