package com.example.mentoria.core.data.remote.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.datastore.entities.DepartamentoEntity
import com.example.mentoria.core.datastore.entities.UsuarioEntity
import com.example.mentoria.core.domain.model.Departamento
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
fun UsuarioDto.toEntity() = UsuarioEntity(
    id = id,
    dni = dni,
    nombre = nombre,
    apellidos = apellidos,
    nfc = nfc,
    password = password,
    rol = Rol.valueOf(rol),
    fechaNacimiento = LocalDate.parse(fechaNacimiento),
    gmail = gmail,
    baja = baja,
    curso = curso,

    // --- CORRECCIÓN AQUÍ ---
    // Convertimos el DTO a Entity para que @Embedded funcione
    departamento = departamento?.let {
        DepartamentoEntity(
            id = it.id,
            nombre = it.nombre
        )
    }
)

fun UsuarioEntity.toDomain() = Usuario(
    id = id,
    dni = dni,
    nombre = nombre,
    apellidos = apellidos,
    nfc = nfc,
    password = password,
    rol = rol,
    fechaNacimiento = fechaNacimiento,
    gmail = gmail,
    baja = baja,
    curso = curso,

    // --- CORRECCIÓN AQUÍ ---
    // Convertimos la Entity de la BD al objeto de Dominio para la UI
    departamento = departamento?.let {
        Departamento(
            id = it.id,
            nombre = it.nombre
        )
    }
)

@RequiresApi(Build.VERSION_CODES.O)
fun UsuarioDto.toDomain() = Usuario(
    id = id,
    dni = dni,
    nfc = nfc,
    password = password,
    rol = Rol.valueOf(rol),
    nombre = nombre,
    apellidos = apellidos,
    fechaNacimiento = LocalDate.parse(fechaNacimiento),
    gmail = gmail,
    baja = baja,
    curso = curso,
    departamento = departamento?.toDomain()
)

/*
@RequiresApi(Build.VERSION_CODES.O)
fun HorarioDto.toDomain() = Horario(
    id = id,
    curso = curso,
    diaSemana = diaSemana,
    horaInicio = LocalTime.parse(horaInicio),
    horaFin = LocalTime.parse(horaInicio)
)
/*
@RequiresApi(Build.VERSION_CODES.O)
fun RegistroAccesoDto.toDomain() = RegistroAcceso(
    id = id,
    fechaHora = LocalDateTime.parse(fechaHora),
    accesoPermitido = accesoPermitido,
    mensaje = mensaje,
    usuario = usuario
)
 */