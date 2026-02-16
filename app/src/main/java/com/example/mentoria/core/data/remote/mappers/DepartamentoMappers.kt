package com.example.mentoria.core.data.remote.mappers

import com.example.mentoria.core.data.remote.dto.DepartamentoDto
import com.example.mentoria.core.data.local.entities.DepartamentoEntity
import com.example.mentoria.core.domain.model.Departamento

fun DepartamentoEntity.toDomain() = Departamento(
    id = id,
    nombre = nombre
)

fun DepartamentoDto.toEntity() = DepartamentoEntity(
    id = id,
    nombre = nombre
)

fun DepartamentoDto.toDomain() = Departamento(
    id = id,
    nombre = nombre
)
