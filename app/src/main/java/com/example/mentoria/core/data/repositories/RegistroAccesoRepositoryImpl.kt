package com.example.mentoria.core.data.repositories

import androidx.compose.runtime.collectAsState
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
    private val usuApi: UsuarioApiService,
    private val usuDao: UsuarioDao,
) : RegistroAccesoRepository {
    override fun getAllRegistros(): Flow<List<RegistroAcceso>> = channelFlow {
        /*
        val local = registroAccesoDao.getAllRegistros()
        local.map { registros ->
            if (registros.isNotEmpty())
                registros.forEach { registro ->
                    usuDao.getUsuarioById(registro.usuarioId).map {
                        registro.toDomain(it.toDomain())
                    }
                }
        }

        val remote = api.getAllRegistros()
        remote.map {
            it.forEach { registro ->
            }
            registroAccesoDao.insertAll(it.toEntity())
        }
        //return local.map { it.toDomain() }
         */
        val job = launch {
            registroAccesoDao.getAllRegistros().map { entities ->
                entities.forEach { registro ->
                    usuDao.getUsuarioById(registro.usuarioId).map {
                        registro.toDomain(it.toDomain())
                    }
                }
            }
        }

        try {
            val remoteRegistros = api.getAllRegistros()
            registroAccesoDao.insertAll(
                registros = remoteRegistros.map { it.map { it.toEntity() } } as List<RegistroAccesoEntity>
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        awaitClose { job.cancel() }
    }

    override fun getRegistro(id: String): Flow<RegistroAcceso> {
        TODO("Not yet implemented")
    }

    override fun getRegistrosFromUsuario(id: String): Flow<List<RegistroAcceso>> = channelFlow {
        val job = launch {
            val usuario = usuDao.getUsuarioById(id).map { usuarioEntity ->
                registroAccesoDao.getAllRegistrosFromUsuario(id).map { entities ->
                    entities.forEach { registro ->
                        registro.toDomain(usuarioEntity.toDomain())
                    }
                }
            }
        }

        try {
            val remoteRegistros = api.getAllRegistros()
            registroAccesoDao.insertAll(
                registros = remoteRegistros.map { it.map { it.toEntity() } } as List<RegistroAccesoEntity>
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