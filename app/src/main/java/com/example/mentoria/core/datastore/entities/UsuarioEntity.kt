package com.example.mentoria.core.datastore.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mentoria.core.domain.model.Departamento
import com.example.mentoria.core.domain.model.Rol
import java.time.LocalDate

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey val id: String,
    val dni: String,
    val password: String,
    val nombre: String,
    val apellidos: String,
    val nfc: String?,
    val fechaNacimiento: LocalDate,
    val gmail: String,
    val rol: Rol,
    @Embedded(prefix = "dep_")
    val departamento: DepartamentoEntity?,
    val baja: Boolean = false,
    val curso: String?
)
