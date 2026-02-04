package com.example.mentoria.core.data.remote.dto

import com.example.mentoria.core.domain.model.Departamento
import com.example.mentoria.core.domain.model.Rol
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class UsuarioDto(
    @SerialName("_id")
    val id: String,
    @SerialName("dni")
    val dni: String,
    @SerialName("nombre")
    val nombre: String,
    @SerialName("apellidos")
    val apellidos: String,
    @SerialName("nfcToken")
    val nfc: String?,
    @SerialName("password")
    val password: String,
    @SerialName("rol")
    val rol: String,
    @SerialName("fechaNacimiento")
    val fechaNacimiento: String,
    @SerialName("gmail")
    val gmail: String,
    @SerialName("departamento")
    val departamento: DepartamentoDto? = null,
    @SerialName("baja")
    val baja: Boolean,
    @SerialName("curso")
    val curso: String?
)
