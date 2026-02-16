package com.example.mentoria.core.domain.repositories

import com.example.mentoria.core.domain.model.RegistroAcceso
import kotlinx.coroutines.flow.Flow

interface RegistroAccesoRepository {
    fun getAllRegistros(): Flow<RegistroAcceso>
    fun getRegistro(): Flow<RegistroAcceso>
    fun getRegistroFromUsuario(): Flow<RegistroAcceso>
    suspend fun deleteRegistro(registroId: String)
    suspend fun createRegistro(registro: RegistroAcceso)
}