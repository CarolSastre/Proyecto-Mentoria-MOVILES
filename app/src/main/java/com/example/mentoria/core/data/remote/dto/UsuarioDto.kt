package com.example.mentoria.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsuarioDto(
    @SerialName("id") val id: String? = null,
    @SerialName("dni") val dni: String? = null,
    @SerialName("nombre") val nombre: String? = null,
    @SerialName("apellidos") val apellidos: String? = null,
    @SerialName("nfc") val nfc: String? = null,
    @SerialName("rol") val rol: String? = null,
    @SerialName("fecha_nacimiento") val fechaNacimiento: String? = null,
    @SerialName("gmail") val gmail: String? = null,
    @SerialName("baja") val baja: Boolean? = null,
    @SerialName("curso") val curso: String? = null,
    @SerialName("departamento") val departamento: DepartamentoDto? = null,
    @SerialName("foto_perfil") val fotoPerfil: String? = null
)