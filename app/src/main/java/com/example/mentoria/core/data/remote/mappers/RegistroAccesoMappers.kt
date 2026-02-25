package com.example.mentoria.core.data.remote.mappers

import com.example.mentoria.core.data.local.entities.RegistroAccesoEntity
import com.example.mentoria.core.data.remote.dto.RegistroAccesoDto
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Usuario
import java.time.LocalDateTime

fun RegistroAccesoEntity.toDomain(usuario: Usuario) = RegistroAcceso(
    id = id,
    fechaHora = fechaHora,
    accesoPermitido = accesoPermitido,
    mensaje = mensaje,
    usuario = usuario
)

fun RegistroAcceso.toEntity() = RegistroAccesoEntity(
    id = id,
    fechaHora = fechaHora,
    accesoPermitido = accesoPermitido,
    mensaje = mensaje,
    usuarioId = usuario.id
)

fun RegistroAccesoDto.toEntity() = RegistroAccesoEntity(
    id = _id,
    fechaHora = LocalDateTime.parse(fechaHora),
    accesoPermitido = accesoPermitido,
    mensaje = mensaje,
    usuarioId = usuario._id ?: ""
)

fun RegistroAccesoDto.toDomain() = RegistroAcceso(
    id = _id,
    fechaHora = LocalDateTime.parse(fechaHora),
    accesoPermitido = accesoPermitido,
    mensaje = mensaje,
    usuario = usuario.toDomain()
)