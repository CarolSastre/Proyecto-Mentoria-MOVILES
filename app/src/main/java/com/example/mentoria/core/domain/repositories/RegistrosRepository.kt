package com.example.mentoria.core.domain.repositories

import com.example.mentoria.core.domain.model.RegistroAcceso

interface RegistrosRepository {
    suspend fun getRegistros(): List<RegistroAcceso>
}