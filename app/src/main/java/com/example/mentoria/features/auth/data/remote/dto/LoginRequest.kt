package com.example.mentoria.features.auth.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("dni")
    val dni: String,
    @SerialName("password")
    val password: String,
)
