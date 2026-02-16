package com.example.mentoria.core.data.repositories

import com.example.mentoria.core.data.local.dao.RegistroDao
import com.example.mentoria.core.data.remote.UsuarioApiService
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.repositories.RegistroAccesoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RegistroAccesoRepositoryImpl(
    private val api: UsuarioApiService,
    private val registroAccesoDao: RegistroDao
): RegistroAccesoRepository {
    override fun getAllRegistros(): Flow<List<RegistroAcceso>> {
        val local = registroAccesoDao.getAllRegistros()
        local.map{ registros ->
            if (registros.isNotEmpty())
            registros.map{
                it
            }
        }

        //return local.map { it.toDomain() }
        /*
        val remote = api
        usuarioDao.insertAll(remote.map { it.toEntity() })
        return remote.map { it.toDomain() }
         */
        TODO("Not yet implemented")
    }

    override fun getRegistro(): Flow<RegistroAcceso> {
        TODO("Not yet implemented")
    }

    override fun getRegistrosFromUsuario(id: String): Flow<List<RegistroAcceso>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRegistro(registroId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createRegistro(registro: RegistroAcceso) {
        TODO("Not yet implemented")
    }
}