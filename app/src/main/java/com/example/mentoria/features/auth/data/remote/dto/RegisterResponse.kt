package com.example.mentoria.features.auth.data.remote.dto

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    @SerialName("nfctoken") val token: String?,
    @SerialName("usuario") val usuario: UsuarioDto?
)
