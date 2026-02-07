package com.example.mentoria.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HorarioDto(
    @SerialName("_id")
    val id: String,
    @SerialName("curso")
    val curso: String,
    @SerialName("diaSemana")
    val diaSemana: String,
    @SerialName("horaInicio")
    val horaInicio: String,
    @SerialName("horaFin")
    val horaFin: String
)
