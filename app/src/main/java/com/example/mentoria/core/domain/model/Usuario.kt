package com.example.mentoria.core.domain.model

import java.time.LocalDate

data class Usuario(
    val id: String,
    val dni: String,
    val password: String,
    val nombre: String,
    val apellidos: String,
    val rol: Rol,
    val fechaNacimiento: LocalDate?,
    val gmail: String,
    val baja: Boolean,

    // CAMBIO: Ahora permite nulos (String?)
    val curso: String?,
    val departamento: Departamento?,
    val nfc: String?,
    val fotoPerfilUrl: String? = null
)