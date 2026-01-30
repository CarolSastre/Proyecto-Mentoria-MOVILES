package com.example.mentoria.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsuarioResponse(
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<UsuarioDto> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)
