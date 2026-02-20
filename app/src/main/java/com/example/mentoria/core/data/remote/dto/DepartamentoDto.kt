package com.example.mentoria.core.data.remote.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class DepartamentoDto(
    @OptIn(ExperimentalSerializationApi::class)
    @JsonNames("_id")
    @SerialName("id")
    val id: String,
    @SerialName("nombre")
    val nombre: String
)
