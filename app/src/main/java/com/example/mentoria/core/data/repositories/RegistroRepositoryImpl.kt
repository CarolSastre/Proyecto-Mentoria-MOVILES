package com.example.mentoria.core.data.repositories

import com.example.mentoria.core.data.remote.RegistroApiService
import com.example.mentoria.core.datastore.dao.RegistroDao
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.repositories.RegistrosRepository

class RegistroRepositoryImpl(
    private val api: RegistroApiService,
    private val registroDao: RegistroDao
): RegistrosRepository {
    override suspend fun getRegistros(): List<RegistroAcceso> {
        TODO("Not yet implemented")
    }
}