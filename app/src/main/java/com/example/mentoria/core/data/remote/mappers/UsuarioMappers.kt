package com.example.mentoria.core.data.remote.mappers

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.data.local.entities.UsuarioEntity
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.domain.model.Rol
import java.time.LocalDate

private const val BASE_URL_IMAGENES = "http://10.0.2.2:8080/uploads/"

fun UsuarioEntity.toDomain(): Usuario {
    return Usuario(
        id = id,
        dni = dni,
        nombre = nombre,
        apellidos = apellidos,
        password = password,
        nfc = nfc,
        rol = rol,
        fechaNacimiento = fechaNacimiento,
        gmail = gmail,
        baja = baja,

        // YA NO DA ERROR: String? a String? es válido
        curso = curso,

        departamento = departamento?.toDomain(),
        fotoPerfilUrl = fotoPerfil?.let { "$BASE_URL_IMAGENES$it" }
    )
}

fun UsuarioDto.toDomain(): Usuario {
    return Usuario(
        id = _id ?: "",
        dni = dni ?: "",
        nombre = nombre ?: "Sin nombre",
        apellidos = apellidos ?: "",
        password = password ?: "",
        nfc = nfc ?: "",
        rol = try { Rol.valueOf(rol ?: "ALUMNO") } catch (e: Exception) { Rol.ALUMNO },
        fechaNacimiento = fechaNacimiento?.let { LocalDate.parse(it) },
        gmail = gmail ?: "",
        baja = baja ?: false,

        // Si el JSON viene null, pasamos null (para profesores)
        curso = curso,

        departamento = departamento?.toDomain(),
        fotoPerfilUrl = fotoPerfil?.let { "$BASE_URL_IMAGENES$it" }
    )
}

fun UsuarioDto.toEntity(): UsuarioEntity {
    return UsuarioEntity(
        id = _id ?: "",
        dni = dni ?: "",
        nombre = nombre ?: "",
        apellidos = apellidos ?: "",
        password = password ?: "",
        nfc = nfc ?: "",
        rol = try { Rol.valueOf(rol ?: "ALUMNO") } catch (e: Exception) { Rol.ALUMNO },
        fechaNacimiento = fechaNacimiento?.let { LocalDate.parse(it) },
        gmail = gmail ?: "",
        baja = baja ?: false,

        // Guardamos null en la BDD si no hay curso
        curso = curso,
        departamento = departamento?.toEntity(),
        fotoPerfil = fotoPerfil
    )
}

fun Usuario.toDto(): UsuarioDto {
    return UsuarioDto(
        _id = id,
        dni = dni,
        nombre = nombre,
        apellidos = apellidos,
        password = password,
        nfc = nfc,
        rol = rol.toString(),
        fechaNacimiento = fechaNacimiento?.toString(),
        gmail = gmail,
        baja = baja,
        curso = curso,
        departamento = departamento?.toDto(),
        fotoPerfil = fotoPerfilUrl
    )
}