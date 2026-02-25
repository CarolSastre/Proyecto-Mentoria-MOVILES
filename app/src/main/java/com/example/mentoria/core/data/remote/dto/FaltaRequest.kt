package com.example.mentoria.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FaltaRequest(
    @SerialName("alumno_id")
    val alumnoId: String,
    @SerialName("asignatura_id")
    val asignaturaId: String,
    @SerialName("observaciones")
    val observaciones: String,
    @SerialName("tipo")
    val tipo: String,
) {
}