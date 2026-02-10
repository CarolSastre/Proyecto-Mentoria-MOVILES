package com.example.mentoria.features.auth.domain.model

data class Usuario(
    val password: String = "",
    val email: String = "",
    val token: String = ""
)
