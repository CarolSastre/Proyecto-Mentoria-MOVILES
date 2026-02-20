package com.example.mentoria.core.domain.model

import java.time.LocalTime

data class Horario(
    val id: String,
    val curso: String,
    val diaSemana: String,
    val horaInicio: LocalTime,
    val horaFin: LocalTime
)