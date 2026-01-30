package com.example.mentoria.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegistroAccesoDto(
    @SerialName("id")
    val id: String = "",
    @SerialName("fechaHora")
    val fechaHora: String = "",
    @SerialName("accesoPermitido")
    val accesoPermitido: Boolean = false,
    @SerialName("mensaje")
    val mensaje: String = "",
    @SerialName("usuario")
    val usuario: UsuarioDto,
)
