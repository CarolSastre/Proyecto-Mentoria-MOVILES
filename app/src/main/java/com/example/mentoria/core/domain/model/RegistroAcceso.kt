package com.example.mentoria.core.domain.model

import java.time.LocalDateTime

data class RegistroAcceso(
    val id: String,
    val fechaHora: LocalDateTime,
    val accesoPermitido: Boolean,
    val mensaje: String,
    val usuario: Usuario
)