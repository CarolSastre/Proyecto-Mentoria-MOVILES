package com.example.mentoria.core.domain.model

import java.time.LocalDate

data class Usuario(
    val id: String,
    val dni: String,
    val password: String,
    val nombre: String,
    val apellidos: String,
    val nfc: String?,
    val fechaNacimiento: LocalDate,
    val gmail: String,
    val rol: Rol,
    val departamento: Departamento?,
    val baja: Boolean = false,
    val curso: String?
)