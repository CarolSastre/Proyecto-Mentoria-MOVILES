package com.example.mentoria.core.data.remote.dto

import com.example.mentoria.core.domain.model.Departamento
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsuarioDto(
    @SerialName("dni")
    val dni: String = "",
    @SerialName("nombre")
    val nombre: String = "",
    @SerialName("apellidos")
    val apellidos: String = "",
    @SerialName("nfc")
    val nfc: String? = "",
    @SerialName("password")
    val password: String = "",
    @SerialName("rol")
    val rol: String = "", // TODO: confirmar si se pueden crear roles o en String
)
