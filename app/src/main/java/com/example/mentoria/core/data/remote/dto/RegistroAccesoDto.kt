package com.example.mentoria.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegistroAccesoDto(
    @SerialName("_id")
    val _id: String,
    @SerialName("fechaHora")
    val fechaHora: String,
    @SerialName("accesoPermitido")
    val accesoPermitido: Boolean,
    @SerialName("mensaje")
    val mensaje: String,
    @SerialName("usuario")
    val usuario: UsuarioDto,
)
