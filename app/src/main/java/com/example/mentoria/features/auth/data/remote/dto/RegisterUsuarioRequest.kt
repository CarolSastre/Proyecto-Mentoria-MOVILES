package com.example.mentoria.features.auth.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterUsuarioRequest(
    @SerialName("dni")
    val dni: String,
    @SerialName("nombre")
    val nombre: String,
    @SerialName("apellidos")
    val apellidos: String,
    @SerialName("password")
    val password: String,
    @SerialName("fechaNacimiento")
    val fechaNacimiento: String,
    @SerialName("email")
    val email: String
)