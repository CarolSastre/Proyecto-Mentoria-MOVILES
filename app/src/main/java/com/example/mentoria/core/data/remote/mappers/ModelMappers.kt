package com.example.mentoria.core.data.remote.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mentoria.core.data.remote.dto.DepartamentoDto
import com.example.mentoria.core.data.remote.dto.HorarioDto
import com.example.mentoria.core.data.remote.dto.RegistroAccesoDto
import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.domain.model.Departamento
import com.example.mentoria.core.domain.model.Horario
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import java.time.LocalDateTime
import java.time.LocalTime

fun DepartamentoDto.toDomain() = Departamento(
    id = id,
    nombre = nombre
)

@RequiresApi(Build.VERSION_CODES.O)
fun HorarioDto.toDomain() = Horario(
    id = id,
    curso = curso,
    diaSemana = diaSemana,
    horaInicio = LocalTime.parse(horaInicio),
    horaFin = LocalTime.parse(horaInicio)
)

@RequiresApi(Build.VERSION_CODES.O)
fun RegistroAccesoDto.toDomain() = RegistroAcceso(
    id = id,
    fechaHora = LocalDateTime.parse(fechaHora),
    accesoPermitido = accesoPermitido,
    mensaje = mensaje,
    usuario = usuario.toDomain()
)

fun UsuarioDto.toDomain() = Usuario(
    dni = dni,
    nfc = nfc,
    password = password,
    rol = Rol.valueOf(rol),
    nombre = nombre,
    apellidos = apellidos
)