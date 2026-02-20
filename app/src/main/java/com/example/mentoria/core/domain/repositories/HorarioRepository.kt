package com.example.mentoria.core.domain.repositories

import com.example.mentoria.core.domain.model.Horario
import kotlinx.coroutines.flow.Flow

interface HorarioRepository {
    fun getAllHorario(): Flow<List<Horario>>
}