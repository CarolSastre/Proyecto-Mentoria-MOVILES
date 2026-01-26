package com.example.mentoria.core.domain.model

import java.util.Date

data class Registro(
    val fecha: Date,
    val usuario: Usuario,
    val torno: Torno
)