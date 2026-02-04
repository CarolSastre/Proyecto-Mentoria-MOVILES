package com.example.mentoria.core.datastore.entities

import kotlinx.serialization.Serializable

@Serializable
data class DepartamentoEntity(
    val id: String,
    val nombre: String
)