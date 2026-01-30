package com.example.mentoria.core.domain.model

data class Usuario(
    val dni: String,
    val password: String,
    val nombre: String,
    val apellidos: String,
    val rol: Any?,
    val nfc: String?
)

enum class Rol {
    ALUMNO, PROFESOR, ADMIN
}
