package com.example.mentoria.features.auth.domain.model

data class UsuarioAuth(
    val dni: String = "",
    val password: String = "",
    val token: String = ""
)
