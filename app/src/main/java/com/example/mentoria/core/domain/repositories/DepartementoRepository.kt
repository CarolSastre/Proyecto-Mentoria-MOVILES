package com.example.mentoria.core.domain.repositories

import com.example.mentoria.core.domain.model.Departamento
import kotlinx.coroutines.flow.Flow

interface DepartementoRepository {
    fun getAllDepartamentos(): Flow<List<Departamento>>
}