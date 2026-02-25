package com.example.mentoria.core.data.repositories

import com.example.mentoria.core.data.local.dao.RegistroDao
import com.example.mentoria.core.data.local.dao.UsuarioDao
import com.example.mentoria.core.data.local.entities.RegistroAccesoEntity
import com.example.mentoria.core.data.remote.UsuarioApiService
import com.example.mentoria.core.data.remote.RegistroApiService
import com.example.mentoria.core.data.remote.mappers.toDomain
import com.example.mentoria.core.data.remote.mappers.toEntity
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.repositories.RegistroAccesoRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class RegistroAccesoRepositoryImpl(
    private val api: RegistroApiService,
    private val registroAccesoDao: RegistroDao,
    private val usuDao: UsuarioDao,
) : RegistroAccesoRepository {
    override fun getRegistro(id: String): Flow<RegistroAcceso> {
        TODO("Not yet implemented")
    }

    override fun getRegistrosFromUsuario(id: String): Flow<List<RegistroAcceso>> = channelFlow {
        val job = launch {
            usuDao.getUsuarioById(id).map { usuarioEntity ->
                registroAccesoDao.getAllRegistrosFromUsuario(id).map { entities ->
                    entities.forEach { registro ->
                        registro.toDomain(usuarioEntity.toDomain())
                    }
                }
            }
        }

        try {
            val remoteRegistros = api.getRegistrosFromAlumno(id)
            registroAccesoDao.insertAll(
                registros = remoteRegistros.map { lista -> lista.map { it.toEntity() } } as List<RegistroAccesoEntity>
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        awaitClose { job.cancel() }
    }

    override suspend fun deleteRegistro(registroId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createRegistro(registro: RegistroAcceso) {
        TODO("Not yet implemented")
    }
}