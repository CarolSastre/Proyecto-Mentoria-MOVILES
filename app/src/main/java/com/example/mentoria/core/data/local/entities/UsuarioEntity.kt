package com.example.mentoria.core.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mentoria.core.domain.model.Rol
import java.time.LocalDate

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey val id: String,
    val dni: String,
    val nombre: String,
    val apellidos: String,
    val password: String,
    val nfc: String,
    val rol: Rol,
    val fechaNacimiento: LocalDate?,
    val gmail: String,
    val baja: Boolean,

    // CAMBIO: Ahora permite nulos (String?)
    val curso: String?,

    @Embedded(prefix = "dep_") val departamento: DepartamentoEntity?,
    val fotoPerfil: String? = null
)