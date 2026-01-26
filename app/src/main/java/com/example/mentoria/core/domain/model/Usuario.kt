package com.example.mentoria.core.domain.model

data class Usuario(
    val dni: String,
    val password: String,
    val name: String,
    val lastName: String,
    val rol: Any?,
    val nfc: NFCToken?,
    val registros: List<Registro> = listOf()
    ///
)