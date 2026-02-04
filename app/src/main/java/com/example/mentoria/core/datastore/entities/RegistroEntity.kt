package com.example.mentoria.core.datastore.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
@Entity(tableName = "registro_accesos")
data class RegistroEntity(
    @PrimaryKey val id: String,
    val fechaHora: LocalDateTime,
    val accesoPermitido: Boolean,
    val mensaje: String,
    val usuario: UsuarioEntity
)
