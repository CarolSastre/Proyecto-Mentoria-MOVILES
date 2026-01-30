package com.example.mentoria.core.data.remote.dto

data class RegistrarUsuarioRequest(
    val dni: String,
    val nombre: String,
    val apellidos: String,
    val email: String,
    val password: String,
)
