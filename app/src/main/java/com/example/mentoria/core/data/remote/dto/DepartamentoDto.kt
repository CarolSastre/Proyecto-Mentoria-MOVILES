package com.example.mentoria.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DepartamentoDto(
    @SerialName("_id")
    val id: String,
    @SerialName("nombre")
    val nombre: String
)
