package com.example.mentoria.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HorarioDto(
    @SerialName("id")
    val id: String = "",
    @SerialName("curso")
    val curso: String = "",
    @SerialName("diaSemana")
    val diaSemana: String = "",
    @SerialName("horaInicio")
    val horaInicio: String = "", // TODO: todas las fechas en string??
    @SerialName("horaFin")
    val horaFin: String = ""
)
