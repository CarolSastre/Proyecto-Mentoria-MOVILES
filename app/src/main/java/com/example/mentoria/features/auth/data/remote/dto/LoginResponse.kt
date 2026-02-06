package com.example.mentoria.features.auth.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse (
    @SerialName("dni")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("token")
    val token: String
)