package com.example.mentoria.core.domain.repositories

import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Usuario
import kotlinx.coroutines.flow.Flow

interface RegistroAccesoRepository {
    fun getRegistro(id: String): Flow<RegistroAcceso>
    fun getRegistrosFromUsuario(id: String): Flow<List<RegistroAcceso>>
    suspend fun deleteRegistro(registroId: String)
    suspend fun createRegistro(registro: RegistroAcceso)
}